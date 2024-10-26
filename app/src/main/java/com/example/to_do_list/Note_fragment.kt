package com.example.to_do_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.to_do_list.databinding.FragmentNoteViewBinding

class Note_fragment : Fragment(R.layout.fragment_note_view) {
   private lateinit var binding: FragmentNoteViewBinding
   private lateinit var adapter: ListNoteAdapter

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

        adapter = ListNoteAdapter(requireActivity(), list)

        binding.lvNote.adapter = adapter

        binding.lvNote.setOnItemClickListener { adapterView, view, i, l ->
            binding.lvNote.setItemChecked(i, true)
        }
    }
}