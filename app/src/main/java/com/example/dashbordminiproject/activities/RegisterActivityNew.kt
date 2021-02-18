package com.example.dashbordminiproject.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dashbordminiproject.R
import com.example.dashbordminiproject.models.api.RegistrationResponseObject
import com.example.dashbordminiproject.models.api.StudentRegisterPostBody
import com.example.dashbordminiproject.retrofit.RFBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_phone_verification.*
import kotlinx.android.synthetic.main.activity_register_new.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class RegisterActivityNew :
    AppCompatActivity(),
    DatePickerDialog.OnDateSetListener {
    private val TAG = "RegisterActivityNew"
    lateinit var phoneNumber: String
    lateinit var otp: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new)

        datePickerET.setOnClickListener {
            showDatePicker(datePickerET)
        }

        phoneNumber = intent.getStringExtra("phoneNumber")!!
        otp = intent.getStringExtra("otp")!!
        PnInRegisterActivity.setText(phoneNumber)
    }

    fun showDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE),
        ).show()
    }

    override fun onDateSet(
        view: DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        datePickerET.setText("$dayOfMonth/${month + 1}/$year ")
    }

    fun registerButtonClicked(view: View) {
        hideKeyboard(this)
        registerButton.startAnimation()
        val built = RFBuilder()
        val studentToRegister = StudentRegisterPostBody(
            otp,
            phoneNumber,
            StuName = stu_name_ET.text.toString(),
            Pin = pin_ET.text.toString()
        )
        val json = Gson().toJson(studentToRegister)
        Log.d(TAG, "registerButtonClicked: json is \n$json")

        val g: Call<RegistrationResponseObject> = built.placeHolderAPI.register(
            studentToRegister
        )



        g.enqueue(object : Callback<RegistrationResponseObject> {
            override fun onResponse(
                call: Call<RegistrationResponseObject>,
                response: Response<RegistrationResponseObject>
            ) {
                if (response.isSuccessful) {

                    Toast.makeText(
                        applicationContext,
                        "Registration Complete",
                        Toast.LENGTH_SHORT
                    ).show()

                    val responseJson = Gson().toJson(response.body())
                    Log.d(TAG, "onResponse: = $responseJson")

                    toUserDetails(
                        response.body()!!.Message,
                        response.body()!!.UserCode,
                        response.body()!!.Status.toString()
                    )

                } else {
                    Log.d(TAG, "onResponse: ${response.code()}  ")
                }
            }

            override fun onFailure(
                call: Call<RegistrationResponseObject>,
                t: Throwable
            ) {
                Toast.makeText(
                    applicationContext,
                    "onFailure $t",
                    Toast.LENGTH_SHORT
                ).show()

                Log.e(TAG, "onFailure: $t", t)
            }

        })

    }
    private fun toUserDetails(
        Message: String,
        UserCode: String,
        Status: String) {
        val intent  = Intent(
            this@RegisterActivityNew ,
            UserDetailsActivity::class.java
        )
        intent.putExtra("Message",Message)
        intent.putExtra("UserCode",UserCode)
        intent.putExtra("Status",Status)
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