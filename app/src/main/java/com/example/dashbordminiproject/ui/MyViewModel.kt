package com.example.dashbordminiproject.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashbordminiproject.models.api.OTP_Response
import com.example.dashbordminiproject.models.api.PhoneNumber
import com.example.dashbordminiproject.retrofit.RFBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel(): ViewModel() {
    var mResponse = MutableLiveData<Response<OTP_Response>>()
    lateinit var phoneNumber: PhoneNumber
    var otp: String = ""
    private val TAG = "MyViewModel"
    fun sendOTP(phoneNumber: PhoneNumber){
        this.phoneNumber = phoneNumber
        viewModelScope.launch {
            val g: Call<OTP_Response> = RFBuilder().placeHolderAPI.sendOTP(
                phoneNumber
            )

            g.enqueue(object : Callback<OTP_Response> {
                override fun onResponse(call: Call<OTP_Response>, response: Response<OTP_Response>) {
                    mResponse.value = response
                    if (response.isSuccessful) {
                        otp = response.body()!!.OTP.str_OTP
                    } else {
                        Log.d(TAG, "onResponse: ${response.code()}  ")
                    }
                }

                override fun onFailure(
                    call: Call<OTP_Response>,
                    t: Throwable
                ) {
                    Log.e(TAG, "onFailure: $t", t)
                }

            })
        }
    }
}