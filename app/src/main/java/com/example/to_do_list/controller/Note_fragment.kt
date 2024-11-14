package com.example.to_do_list.controller

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.R
import com.example.to_do_list.data.MyHelper
import com.example.to_do_list.databinding.FragmentNoteViewBinding
import com.example.to_do_list.model.ListNote
import com.example.to_do_list.model.Rv_NoteAdapter

class Note_fragment : Fragment(R.layout.fragment_note_view) {
    private lateinit var binding: FragmentNoteViewBinding
    private lateinit var adapter: Rv_NoteAdapter
    private lateinit var db: SQLiteDatabase
    private lateinit var rs: Cursor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteViewBinding.bind(view)

        val helper = MyHelper(requireContext())
        db = helper.readableDatabase
        rs = db.rawQuery("SELECT *FROM TODOLIST", null)

        val list = mutableListOf<ListNote>()
        if (rs.moveToFirst()) {
            do {
                val note = rs.getString(rs.getColumnIndexOrThrow("NOTE"))
                val date = rs.getString(rs.getColumnIndexOrThrow("DATETIME"))
                list.add(ListNote(note, date))
            } while (rs.moveToNext())
        }

        adapter = Rv_NoteAdapter(requireActivity(), list)

        binding.rvListNote.adapter = adapter

        binding.rvListNote.layoutManager = LinearLayoutManager(requireActivity())
    }
}