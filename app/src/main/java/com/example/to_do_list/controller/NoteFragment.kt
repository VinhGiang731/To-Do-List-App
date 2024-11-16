package com.example.to_do_list.controller

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.R
import com.example.to_do_list.data.MyHelper
import com.example.to_do_list.databinding.FragmentNoteViewBinding
import com.example.to_do_list.model.ItemNoteClick
import com.example.to_do_list.model.ListNote
import com.example.to_do_list.model.Rv_NoteAdapter

class NoteFragment : Fragment(R.layout.fragment_note_view) {
    private lateinit var binding: FragmentNoteViewBinding
    private lateinit var adapter: Rv_NoteAdapter
    private lateinit var db: SQLiteDatabase
    private lateinit var rs: Cursor
    private lateinit var list: MutableList<ListNote>

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteViewBinding.bind(view)

        //Tạo dữ liệu ban đầu để test
        val helper = MyHelper(requireContext())
        list = mutableListOf()
        db = helper.readableDatabase
        getValueDataBase()

        adapter = Rv_NoteAdapter(requireActivity(), list, object : ItemNoteClick {
            override fun onClickNote(pos: Int) {
                Toast.makeText(requireActivity(), "Note: ${list[pos].txt_note}", Toast.LENGTH_SHORT)
                    .show()
                removeNote(pos)
            }
        })

        binding.rvListNote.adapter = adapter
        binding.rvListNote.layoutManager = LinearLayoutManager(requireActivity())

    }

    private fun getValueDataBase() {
        if (::rs.isInitialized && !rs.isClosed) {
            rs.close()
        }

        rs = db.rawQuery("SELECT * FROM TODOLIST", null)
        list.clear()

        if (rs.moveToFirst()) {
            do {
                val idNote = rs.getString(rs.getColumnIndexOrThrow("_id"))
                val note = rs.getString(rs.getColumnIndexOrThrow("NOTE"))
                val date = rs.getString(rs.getColumnIndexOrThrow("DATETIME"))
                list.add(ListNote(idNote, note, date))
            } while (rs.moveToNext())
        }
        rs.close()
    }

    // việc dùng SQLite chưa được tốt, nên chuyển sang firebase để hon thành đồ án này và học lại SQLite sau
    private fun removeNote(pos: Int) {
        val dialog = AlertDialog.Builder(requireActivity())
        dialog.apply {
            setTitle("Confirm")
            setMessage("Are you sure this is done?")
            setIcon(R.drawable.img_warning)

            setNegativeButton("No") { dialogIT: DialogInterface, _: Int ->
                dialogIT.dismiss()
            }

            setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                val noteToRemove = list[pos]

                db.delete(
                    "TODOLIST", "_id = ?", arrayOf(noteToRemove.idNote)
                )

                list.removeAt(pos)
                adapter.notifyItemRemoved(pos)
                adapter.notifyItemRangeChanged(pos, list.size)

            }
        }
        dialog.show()
    }
}