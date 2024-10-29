package com.example.to_do_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.to_do_list.databinding.FragmentScheduleViewBinding

class Schedule_fragment : Fragment(R.layout.fragment_schedule_view) {
    private lateinit var binding: FragmentScheduleViewBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleViewBinding.bind(view)



        binding.cldSchedule.setOnDateChangeListener { calendarView, i, i2, i3 ->
            /*
            calendarView: view
            i: year
            i2: month
            i3: day
             */
            Toast.makeText(requireContext(), "${i3}/${i2+1}/${i}", Toast.LENGTH_SHORT).show()
        }
    }


}