package com.example.to_do_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.databinding.FragmentNoteViewBinding

class Note_fragment : Fragment(R.layout.fragment_note_view) {
   private lateinit var binding: FragmentNoteViewBinding
   private lateinit var adapter: Rv_NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteViewBinding.bind(view)

        val list = mutableListOf<ListNote>()
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))
        list.add(ListNote("day chi la doan note tao lao ma tao viet khi deo co Evkey, va chi co the thoi. bien", "31/07/2004"))

        adapter = Rv_NoteAdapter(requireActivity(), list)

        binding.rvListNote.adapter = adapter

        binding.rvListNote.layoutManager = LinearLayoutManager(requireActivity())
    }
}