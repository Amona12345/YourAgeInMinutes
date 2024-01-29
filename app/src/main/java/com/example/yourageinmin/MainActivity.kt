package com.example.yourageinmin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var button:Button?=null
    var selecteddate :TextView? = null
    var selectedinmin :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.bttn)
       selecteddate=findViewById(R.id.your)
        selectedinmin=findViewById(R.id.ageinmin)
        button?.setOnClickListener {
            getSelectedDate()

        }
    }

    private fun getSelectedDate() {
        val myCalender :Calendar =Calendar.getInstance()
       val year:Int= myCalender.get(Calendar.YEAR)
       val month:Int= myCalender.get(Calendar.MONTH)
       val dayOfMonth:Int= myCalender.get(Calendar.DAY_OF_MONTH)
       val dpd = DatePickerDialog(this,{ view,selectedYear ,SelectedMonth,SelectedDayOFMonth ->
           val selectedDate="$SelectedDayOFMonth/${SelectedMonth + 1}/$selectedYear"
           selecteddate?.text=selectedDate

           val sdf =SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
          val myDate= sdf.parse(selectedDate)
           myDate.let {
               val selectedDateInMin=myDate.time/60000
               val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
               currentDate.let {
                   val currentDateInMin =currentDate.time/60000
                   val diff=currentDateInMin-selectedDateInMin
                   selectedinmin?.text=diff.toString()
               }
           }
       },year,month,dayOfMonth)
        dpd.datePicker.maxDate=System.currentTimeMillis()
        dpd.show()

    }
}