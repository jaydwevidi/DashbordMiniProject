package com.example.dashbordminiproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dashbordminiproject.R
import com.example.dashbordminiproject.models.api.OTP_Response
import com.example.dashbordminiproject.models.api.PhoneNumber
import com.example.dashbordminiproject.retrofit.RFBuilder
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
    fun sendOTP(veiw: View){
        hideKeyboard(this)
        phoneVerificationButton.startAnimation()
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
                        "$otp sent to +91 $phoneNumber",
                        Toast.LENGTH_LONG
                    ).show()

                    OTP_Verifcation_activity(phoneNumber, otp)
                } else {
                    Log.d(TAG, "onResponse: ${response.code()}  ")
                }
            }

            override fun onFailure(
                call: Call<OTP_Response>,
                t: Throwable
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
        otp: String
    ) {
        val intent = Intent(applicationContext, OtpVerificationActivity::class.java)
        intent.putExtra("phoneNumber", "+91$phoneNumber")
        intent.putExtra("otp", otp)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left )
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}