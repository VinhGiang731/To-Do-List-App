package com.example.to_do_list.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.R
import com.example.to_do_list.data.MyHelper
import com.example.to_do_list.databinding.ActivityScheduleBinding
import com.example.to_do_list.databinding.CustomDialogConfirmBinding
import java.util.Calendar

class InsertScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var db: SQLiteDatabase
    private var flag = false
    private var scheduleID: String? = null;
    private val toDay = Calendar.getInstance()
    private var daySchedule: String? = null
    private lateinit var dialog: AlertDialog

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
        val day = toDay.get(Calendar.DAY_OF_MONTH)
        val month = toDay.get(Calendar.MONTH)
        val year = toDay.get(Calendar.YEAR)
        val hour = toDay.get(Calendar.HOUR_OF_DAY)
        val minute = toDay.get(Calendar.MINUTE)

        //pick date
        DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
                var s = "$i3-${i2 + 1}-$i"
                daySchedule = i3.toString()

                //pick time
                TimePickerDialog(
                    this, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                        val amPm = if (hourOfDay <= 12) "a.m" else "p.m"
                        s += " $hourOfDay:$minute($amPm)"
                        txt.text = s
                    },
                    hour, minute, true
                ).show()

            }, year, month, day
        ).show()

        Toast.makeText(this, daySchedule, Toast.LENGTH_SHORT).show()
    }

    //get value được truyền từ activity trước

    /*
    mai phai cho adapter biet co su thay doi va kiem tra lai item schedule
     */
    private fun GetIntentPutExtras() {
        scheduleID = intent.getStringExtra("_id")
        daySchedule = intent.getStringExtra("day")
        val title = intent.getStringExtra("title")
        val fullDay = intent.getIntExtra("fullday", 0)
        val timeStart = intent.getStringExtra("timestart")
        val timeEnd = intent.getStringExtra("timeend")
        val place = intent.getStringExtra("place")
        val notes = intent.getStringExtra("notes")

        binding.edtTitle.setText(title)
        if (fullDay == 1) {
            binding.swtFullday.isChecked = true
        }
        binding.txtStart.text = timeStart
        binding.txtFinish.text = timeEnd
        binding.edtPlace.setText(place)
        binding.edtNotes.setText(notes)

    }

    private fun AddVentBtnBack() {
        binding.btnBack.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val dialogBinding = CustomDialogConfirmBinding.inflate(LayoutInflater.from(this))
            dialogBuilder.setView(dialogBinding.root)

            dialogBinding.btnYes.setOnClickListener {
                finish()
            }

            dialogBinding.btnNo.setOnClickListener {
                dialog.dismiss()
            }

            dialog = dialogBuilder.create()
            dialog.show()


            /*val dialog = AlertDialog.Builder(this)
            dialog.apply {
                setTitle("Confirm")
                setMessage("Do you want to exit?")
                setIcon(R.drawable.ic_confirm)

                setNegativeButton("No") { dialogIT: DialogInterface, _: Int ->
                    dialogIT.dismiss()
                }

                setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                    finish()
                }
            }

            dialog.show()*/
        }
    }

    override fun onPause() {
        super.onPause()
        updateScheduleInDatabase()
    }

    private fun updateScheduleInDatabase() {
        if (flag) {
            val title = binding.edtTitle.text.toString()
            val fullday = binding.swtFullday.isChecked
            val start = binding.txtStart.text.toString()
            val finish = binding.txtFinish.text.toString()
            val place = binding.edtPlace.text.toString()
            val notes = binding.edtNotes.text.toString()

            val contentValue = ContentValues().apply {
                put("DAY", daySchedule)
                put("TITLE", title)
                put("FULLDAY", fullday)
                put("TIMESTART", start)
                put("TIMEEND", finish)
                put("PLACE", place)
                put("NOTES", notes)
            }

            db.update("SCHEDULE", contentValue, "_id = ?", arrayOf(scheduleID))
        }
    }
}