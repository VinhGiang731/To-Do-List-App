package com.example.to_do_list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.databinding.ActivityMakenoteBinding
import com.google.android.material.tabs.TabLayoutMediator

class MakeNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakenoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMakenoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AddEventBtnMore()
        ViewPagerSetup()
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