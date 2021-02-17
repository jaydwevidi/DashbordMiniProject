package com.example.dashbordminiproject.retrofit

import com.example.dashbordminiproject.models.api.RegistrationResponseObject
import com.example.dashbordminiproject.models.api.StudentRegisterPostBody
import com.example.dashbordminiproject.models.api.OTP_Response
import com.example.dashbordminiproject.models.api.PhoneNumber
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

    @POST("SignUp_VerifyOTP")
    fun register(@Body body : StudentRegisterPostBody):Call<RegistrationResponseObject>

}