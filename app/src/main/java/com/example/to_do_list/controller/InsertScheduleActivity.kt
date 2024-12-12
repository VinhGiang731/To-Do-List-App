package com.example.to_do_list.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.data.MyHelper
import com.example.to_do_list.databinding.ActivityScheduleBinding
import java.util.Calendar

class InsertScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var db: SQLiteDatabase
    private var flag = false
    private var scheduleID: String? = null;
    val today = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val helper = MyHelper(this)
        db = helper.readableDatabase
        flag = intent.getBooleanExtra("flag", false)

        AddVentBtnBack()
        if (flag) {
            GetIntentPutExtras()
        }

        AddEventBtnCombobox()

    }

    //mai phải hoàn thành sự kiện chọn time start và end
    private fun AddEventBtnCombobox() {
        binding.btnPickDateStart.setOnClickListener {
            datePicker(binding.txtStart)
        }

        binding.btnPickDateFinish.setOnClickListener {
            datePicker(binding.txtFinish)
        }
    }

    private fun datePicker(txt: TextView) {
        val day = today.get(Calendar.DAY_OF_MONTH)
        val month = today.get(Calendar.MONTH)
        val year = today.get(Calendar.YEAR)
        val hour = today.get(Calendar.HOUR_OF_DAY)
        val minute = today.get(Calendar.MINUTE)

        //pick date
        DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                var s = "$i3-${i2 + 1}-$i"

                //pick time
                TimePickerDialog(
                    this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        s += " $hourOfDay:$minute"
                        txt.text = s
                    },
                    hour, minute, true
                ).show()

            }, year, month, day
        ).show()
    }

    //get value được truyền từ activity trước

    /*
    mai phai cho adapter biet co su thay doi va kiem tra lai item schedule
     */
    private fun GetIntentPutExtras() {
        scheduleID = intent.getStringExtra("_id")
        val day = intent.getStringExtra("day")
        val title = intent.getStringExtra("title")
        val fullday = intent.getIntExtra("fullday", 0)
        val timeStart = intent.getStringExtra("timestart")
        val timeEnd = intent.getStringExtra("timeend")
        val place = intent.getStringExtra("place")
        val notes = intent.getStringExtra("notes")

        binding.edtTitle.setText(title)
        if (fullday == 1) {
            binding.swtFullday.isChecked = true
        }
        binding.txtStart.setText(timeStart)
        binding.txtFinish.setText(timeEnd)
        binding.edtPlace.setText(place)
        binding.edtNotes.setText(notes)

    }

    private fun AddVentBtnBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        updateScheduleInDatabase()
    }

    private fun updateScheduleInDatabase() {
        if(flag){
            val title = binding.edtTitle.text.toString()
            val fullday = false
            val start = binding.txtStart.text.toString()
            val finish = binding.txtFinish.text.toString()

            val contentValue = ContentValues().apply {
                put("TITLE", title)
                put("FULLDAY", fullday)
                put("TIMESTART", start)
                put("TIMEEND", finish)
            }

            db.update("SCHEDULE", contentValue, "_id = ?", arrayOf(scheduleID))
        }
    }
}