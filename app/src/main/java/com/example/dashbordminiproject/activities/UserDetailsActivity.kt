package com.example.dashbordminiproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dashbordminiproject.R
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {
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