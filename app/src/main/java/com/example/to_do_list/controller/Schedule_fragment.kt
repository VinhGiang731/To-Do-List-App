package com.example.to_do_list.controller

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.R
import com.example.to_do_list.data.MyHelper
import com.example.to_do_list.databinding.FragmentScheduleViewBinding
import com.example.to_do_list.model.Item_Click
import com.example.to_do_list.model.ListSchedule
import com.example.to_do_list.model.Rv_ScheduleAdepter

class Schedule_fragment : Fragment(R.layout.fragment_schedule_view) {
    private lateinit var binding: FragmentScheduleViewBinding
    private lateinit var adapter: Rv_ScheduleAdepter
    private lateinit var list: MutableList<ListSchedule>
    private lateinit var rs: Cursor
    private lateinit var db: SQLiteDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleViewBinding.bind(view)

        val helper = MyHelper(requireContext())
        list = mutableListOf()
        db = helper.readableDatabase
        getValueDataBase()

        if (list.isEmpty()) {
            binding.txtNotification.visibility = View.VISIBLE
            binding.rvListSchedule.visibility = View.GONE
        } else {
            binding.rvListSchedule.visibility = View.VISIBLE
            setUpAdapter()
        }
    }

    private fun getValueDataBase() {
        if (::rs.isInitialized && !rs.isClosed) {
            rs.close()
        }

        rs = db.rawQuery("SELECT * FROM SCHEDULE", null)
        list.clear()

        if (rs.moveToFirst()) {
            do {
                val day = rs.getString(rs.getColumnIndexOrThrow("DAY"))
                val title = rs.getString(rs.getColumnIndexOrThrow("TITLE"))
                val time = rs.getString(rs.getColumnIndexOrThrow("TIME"))
                val place = rs.getString(rs.getColumnIndexOrThrow("PLACE"))
                val notes = rs.getString(rs.getColumnIndexOrThrow("NOTES"))
                list.add(ListSchedule(day, title, time, place, notes))
            } while (rs.moveToNext())
        }
        rs.close()
    }

    private fun setUpAdapter() {
        adapter = Rv_ScheduleAdepter(requireActivity(), list, object : Item_Click {
            override fun onLongClickNote(pos: Int) {
                Toast.makeText(requireActivity(), "${pos} long", Toast.LENGTH_SHORT).show()
            }

            override fun onClickNote(pos: Int) {
                Toast.makeText(requireActivity(), "${pos} long", Toast.LENGTH_SHORT).show()
                val i = Intent(requireActivity(), InsertScheduleActivity::class.java)
                startActivity(i)
            }
        })

        binding.rvListSchedule.adapter = adapter
        binding.rvListSchedule.layoutManager = LinearLayoutManager(requireActivity())
    }


}