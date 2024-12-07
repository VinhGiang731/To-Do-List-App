package com.example.to_do_list.controller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.databinding.ActivityScheduleBinding

class InsertScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private var flag = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        flag = intent.getBooleanExtra("flag", false)

        AddeventBtnBack()
        if (flag) {
            GetIntentPutExtras()
        }

    }

    private fun GetIntentPutExtras() {
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

    private fun AddeventBtnBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}