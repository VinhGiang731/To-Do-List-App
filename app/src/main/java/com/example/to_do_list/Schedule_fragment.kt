package com.example.to_do_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.to_do_list.databinding.FragmentScheduleViewBinding


class Schedule_fragment : Fragment(R.layout.fragment_schedule_view) {
   private lateinit var binding: FragmentScheduleViewBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleViewBinding.inflate(layoutInflater)
    }


}