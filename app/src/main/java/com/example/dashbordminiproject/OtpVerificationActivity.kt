package com.example.dashbordminiproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_otp_verification.*

class OtpVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        val phoneNumber = intent.getStringExtra("phoneNumber")
        phoneNumberTV.text = "+91 $phoneNumber"
        val otp = intent.getStringExtra("otp")
        Log.d("TAG", "otpIS: $otp ")

        otpVerificationButton.setOnClickListener {
            otpVerificationResultTV.visibility = View.VISIBLE
            val otpFromET = otpPinView.text.toString().substring(0,4)
            if (otpFromET == otp) {
                val intent = Intent(
                    applicationContext,
                    RegisterActivityNew::class.java
                )
                intent.putExtra("phoneNumber", phoneNumber)
                startActivity(intent)
                otpVerificationResultTV.text = "Verification Complete"
            }
            else {
                otpVerificationResultTV.text = "Verification Failed Invalid OTP Try Again !!"
                otpPinView.setText("")
            }

        }
    }
}