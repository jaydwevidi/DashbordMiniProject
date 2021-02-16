package com.example.dashbordminiproject.retrofit

import com.example.dashbordminiproject.models.CompleteData
import com.example.otptest.models.OTP_Response
import com.example.otptest.models.PhoneNumber
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonGetter {

    @GET("CityList")
    fun getCityList(): Call<String>

    /*@GET("CityList")
    fun getColours() : Call<List<Colour>>*/

    @POST("SignUp_SendOTP")
    fun sendOTP(@Body number : PhoneNumber):Call<OTP_Response>
}