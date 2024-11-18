package com.example.to_do_list.controller

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
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

        //adapter cho recycle view
        adapter = Rv_NoteAdapter(requireActivity(), list, object : ItemNoteClick {

            //override lai 2 phuong thuc nhan long va nhan short cho item rvListNode
            override fun onLongClickNote(pos: Int) {
                Toast.makeText(requireActivity(), "Note: ${list[pos].txt_note}", Toast.LENGTH_SHORT)
                    .show()
                //truyen position vao de no co the xoa dung vi tri node va cap nhat lai db
                removeNote(pos)
            }

            override fun onClickNote(pos: Int) {
                //truyen positon tuong tu tren
                AddEventClickItem(pos)
            }
        })

        //set adapter cho rvListNode
        binding.rvListNote.adapter = adapter
        binding.rvListNote.layoutManager = LinearLayoutManager(requireActivity())

    }

    //fun nay dung de goi activity InsertNote
    private fun AddEventClickItem(pos: Int) {
        val i = Intent(requireActivity(), InsertNoteActivity::class.java)
        //truyen du lieu theo pos cua list (lis[pos]: item dang click)
        //gui sang activity kia de show cho nguoi dung xem
        i.putExtra("_id", list[pos].idNote)
        i.putExtra("Title", list[pos].txt_note)
        i.putExtra("Content", list[pos].txt_content)
        startActivity(i)
    }

    //ham onResume trong vong doi cua ung dung
    //khi thoat activity dang de len activity NoteFrag thi no se tu dong lam nhung thu trong ham
    //o day no se get lai value trong db da thay doi ben activity kia va bao cho adapter de set lai list
    //-----hien tai dang loi o adapter.notify mai nho sua-----
    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        getValueDataBase()
        adapter.notifyDataSetChanged()
    }

    //ham get value tu db
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
                val content = rs.getString(rs.getColumnIndexOrThrow("CONTENT"))
                val date = rs.getString(rs.getColumnIndexOrThrow("DATETIME"))
                list.add(ListNote(idNote, note, content, date))
            } while (rs.moveToNext())
        }
        rs.close()
    }

    //ham remove item list
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