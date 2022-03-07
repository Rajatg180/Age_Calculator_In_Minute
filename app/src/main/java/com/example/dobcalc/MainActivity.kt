package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    //creating a nullable variable and set it to null
    private var tvSelectedDate : TextView?=null
    private var tvAgeInMinute : TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker:Button=findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.selectedDate)
        tvAgeInMinute=findViewById(R.id.tvAgeInMinute)
        btnDatePicker.setOnClickListener {
             // selecting date using calender
            val mycalender= Calendar.getInstance()
            val year=mycalender.get(Calendar.YEAR)
            val month=mycalender.get(Calendar.MONTH)
            val day=mycalender.get(Calendar.DAY_OF_MONTH)
            val dpd=DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, year, month,dayOfMonth->
                    //Toast.makeText(this,"Btn pressed",Toast.LENGTH_LONG).show()
                    val selectedDate="${dayOfMonth}/${month+1}/${year}"
                    tvSelectedDate?.text=selectedDate
                    val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                    val thedata=sdf.parse(selectedDate)
                    thedata?.let{
                        val selectedDateInMinute=thedata.time/60000
                        val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                      currentDate?.let { val currentDateInMinute=currentDate.time/60000
                          val differenceInMinutes=currentDateInMinute-selectedDateInMinute
                          tvAgeInMinute?.text=differenceInMinutes.toString()
                      }
                    }


                },
                year,
                month,
                day
            )
            dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
            dpd.show()
        }
    }


}