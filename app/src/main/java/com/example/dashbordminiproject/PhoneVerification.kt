package com.example.dashbordminiproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dashbordminiproject.retrofit.RFBuilder
import com.example.otptest.models.OTP_Response
import com.example.otptest.models.PhoneNumber
import kotlinx.android.synthetic.main.activity_phone_verification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneVerification : AppCompatActivity() {
    private val TAG = "PhoneVerification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)
    }
    fun sendOTP(veiw : View){
        phoneVerificationButton.isClickable = false
        val built = RFBuilder()
        val phoneNumber = phoneNumberET.text.toString().trim()
        val g: Call<OTP_Response> = built.placeHolderAPI.sendOTP(
            PhoneNumber("+91$phoneNumber")
        )
        g.enqueue(object : Callback<OTP_Response> {
            override fun onResponse(call: Call<OTP_Response>, response: Response<OTP_Response>) {
                if (response.isSuccessful) {
                    val otp = response.body()!!.OTP.str_OTP
                    Log.d(TAG, "onResponse OTP is: $otp")
                    Toast.makeText(
                        applicationContext,
                        "otp sent to +91 $phoneNumber",
                        Toast.LENGTH_SHORT
                    ).show()

                    OTP_Verifcation_activity(phoneNumber,otp)
                } else {
                    Log.d(TAG, "onResponse: ${response.code()}  ")
                }
            }

            override fun onFailure(
                call: Call<OTP_Response>,
                t   : Throwable
            ) {
                Toast.makeText(
                    applicationContext,
                    "$t",
                    Toast.LENGTH_SHORT
                ).show()

                Log.e(TAG, "onFailure: $t", t)
            }

        })
    }

    private fun OTP_Verifcation_activity(
        phoneNumber: String,
        otp:String
    ) {
        val intent = Intent(applicationContext , OtpVerificationActivity::class.java)
        intent.putExtra("phoneNumber", phoneNumber)
        intent.putExtra("otp", otp)
        startActivity(intent)
    }

}