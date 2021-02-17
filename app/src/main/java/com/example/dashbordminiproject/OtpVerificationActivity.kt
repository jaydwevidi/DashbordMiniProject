package com.example.dashbordminiproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_otp_verification.*

class OtpVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        val phoneNumber = intent.getStringExtra("phoneNumber")
        phoneNumberTV.text = "+91 $phoneNumber"
        val otp = intent.getStringExtra("otp")

        otpPinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.length == 4 && s.toString()==otp)
                        startOTPVerification()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        }

        )

        otpVerificationButton.setOnClickListener {
            startOTPVerification()
        }
    }
    fun startOTPVerification(){
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val otp = intent.getStringExtra("otp")
        otpVerificationResultTV.visibility = View.VISIBLE
        Log.d("TAG", "otpIS: $otp ")
        val otpFromET = otpPinView.text.toString()
        if (otpFromET == otp) {
            val intent = Intent(
                applicationContext,
                RegisterActivityNew::class.java
            )
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
            //otpVerificationResultTV.text = "Verification Complete"
        }
        else {
            otpVerificationResultTV.text = "Verification Failed Invalid OTP Try Again !!"
            otpPinView.setText("")
        }
    }
}