package com.example.to_do_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.to_do_list.databinding.FragmentNoteViewBinding

class Note_fragment : Fragment(R.layout.fragment_note_view) {
   private lateinit var binding: FragmentNoteViewBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteViewBinding.inflate(layoutInflater)


    }
}