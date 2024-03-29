package com.example.dashbordminiproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dashbordminiproject.R
import com.example.dashbordminiproject.ui.MyViewModel
import kotlinx.android.synthetic.main.activity_otp_verification.*

class OtpVerificationActivity : AppCompatActivity() {

    lateinit var phoneNumber: String
    lateinit var otp: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        val myViewModel: MyViewModel by viewModels()
        phoneNumber = myViewModel.otp
        phoneNumber = intent.getStringExtra("phoneNumber")!!
        phoneNumberTV.text = phoneNumber
        otp = intent.getStringExtra("otp")!!

        setupTextChangeListeners()
        otpVerificationButton.setOnClickListener {
            startOTPVerification()
        }
    }

    private fun setupTextChangeListeners() {
        otpPinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.length == 4 && s.toString() == otp)
                        startOTPVerification()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    fun startOTPVerification() {
        if (otpPinView.text.toString() == otp) {

            val intent = Intent(
                applicationContext,
                UserRegisterActivity::class.java
            )
            intent.putExtra("phoneNumber", phoneNumber)
            intent.putExtra("otp", otp)
            startActivity(intent)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left )
        } else {
            //otpVerificationResultTV.text = "Verification Failed Invalid OTP Try Again !!"
            otpPinView.setText("")
        }
    }
    fun showToastOTP(view: View){
        Toast.makeText(applicationContext, otp, Toast.LENGTH_LONG).show()
    }
}