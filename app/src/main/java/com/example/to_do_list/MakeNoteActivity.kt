package com.example.to_do_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.databinding.ActivityMakenoteBinding
import com.google.android.material.tabs.TabLayoutMediator

class MakeNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakenoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMakenoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AddEventBtnMore()
        ViewPagerSetup()
        AddEvenBtnFloat()
    }

    private fun AddEvenBtnFloat() {
        var flag = false
        binding.btnBassFloat.setOnClickListener {
            flag = !flag
            if(flag) {
                binding.btnBassFloat.setImageResource(R.drawable.bg_btn_close)
                binding.btnSubFloat1.visibility = View.VISIBLE
                binding.btnSubFloat2.visibility = View.VISIBLE
            } else{
                binding.btnBassFloat.setImageResource(R.drawable.bg_btn_float)
                binding.btnSubFloat1.visibility = View.GONE
                binding.btnSubFloat2.visibility = View.GONE
            }

        }
    }

    private fun ViewPagerSetup() {
        val adt = viewPagerAdapter(supportFragmentManager, lifecycle)
        binding.pgForTablayout.adapter = adt

        TabLayoutMediator(binding.tblOptions, binding.pgForTablayout) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.schedule)
                }

                1 -> {
                    tab.text = getString(R.string.note)
                }
            }
        }.attach()
    }

    private fun AddEventBtnMore() {
        binding.btnMore.setOnClickListener {
            val intent = Intent(this, MoreActivity::class.java)
            startActivity(intent)
        }
    }
}