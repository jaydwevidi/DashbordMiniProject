package com.example.dashbordminiproject.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dashbordminiproject.R

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        var userStringDetails = ""
        userStringDetails += "Message     :\n"
        userStringDetails += intent.getStringExtra("Message")
        userStringDetails += "\n\n\n"
        userStringDetails += "UserCode    :\n"
        userStringDetails += intent.getStringExtra("UserCode")
        userStringDetails += "\n\n\n"
        userStringDetails += "\nStatus      :\n"
        userStringDetails += intent.getStringExtra("Status")
    }
}