package com.example.form

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.form.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        binding.registerBtn.setOnClickListener {
            if (binding.firstnameInput.text.isEmpty() || binding.lastnameInput.text.isEmpty()
                || binding.genderInput.getCheckedRadioButtonId() == -1 || binding.birthdayInput.text.isEmpty()
                || binding.addressInput.text.isEmpty() || binding.emailInput.text.isEmpty() || ! binding.checkboxInput.isChecked()) {
                Toast.makeText(applicationContext, "Please fill in all field", Toast.LENGTH_LONG)
                    .show()

            }else{
                Toast.makeText(applicationContext, "Register successfully", Toast.LENGTH_LONG)
                    .show()
            }

        }

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        binding.birthdayBtn.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.birthdayInput.setText(sdf.format(myCalendar.time))
    }


}