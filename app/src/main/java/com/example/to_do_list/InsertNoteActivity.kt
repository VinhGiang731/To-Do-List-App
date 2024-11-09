package com.example.to_do_list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.databinding.ActivityInsertNoteBinding

class InsertNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInsertNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AddEventBtnBack()
    }

    private fun AddEventBtnBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}