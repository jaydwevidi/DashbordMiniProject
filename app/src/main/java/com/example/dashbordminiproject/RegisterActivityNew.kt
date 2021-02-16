package com.example.dashbordminiproject
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register_new.*
import java.util.*

class RegisterActivityNew :
    AppCompatActivity(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new)

        datePickerET.setOnClickListener {
            showDatePicker(datePickerET)
        }

        val phoneNumber = intent.getStringExtra("phoneNumber")
        PnInRegisterActivity.setText( phoneNumber )
    }

    fun showDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE),
        )
        datePickerDialog.show()
    }

    override fun onDateSet(
        view: DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int) {
        datePickerET.text = "$dayOfMonth/${month + 1}/$year "
    }

    fun registerButtonClicked(view: View){
        Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()
        finish()
    }
}