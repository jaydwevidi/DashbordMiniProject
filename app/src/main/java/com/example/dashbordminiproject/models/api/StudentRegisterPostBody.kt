package com.example.dashbordminiproject.models.api

data class StudentRegisterPostBody(
    val OTP : String ,
    val PhoneNo:String ,
    val Pin:String  = "Test Pin",
    val ReffrelCode:String = "",
    val StuAddress:String ="sample Address , Lucknow",
    val StuEmail:String ="test-email.com",
    val StuName:String = "Test Name",
    val VerificationStatus:Boolean = true
)
