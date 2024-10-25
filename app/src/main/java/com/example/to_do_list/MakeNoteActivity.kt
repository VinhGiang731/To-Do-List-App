package com.example.to_do_list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.databinding.ActivityMakenoteBinding

class MakeNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakenoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMakenoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AddEventBtnMore()
    }

    private fun AddEventBtnMore() {
        binding.btnMore.setOnClickListener {
            val intent = Intent(this, MoreActivity::class.java)
            startActivity(intent)
//            finish()
        }
    }
}