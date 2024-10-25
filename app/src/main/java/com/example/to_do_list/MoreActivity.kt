package com.example.to_do_list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.databinding.ActivityMoreBinding

class MoreActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AddEventBtnMore()
    }

    private fun AddEventBtnMore() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}