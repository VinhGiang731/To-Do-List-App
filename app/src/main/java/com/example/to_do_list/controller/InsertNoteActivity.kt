package com.example.to_do_list.controller

import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.R
import com.example.to_do_list.data.MyHelper
import com.example.to_do_list.databinding.ActivityInsertNoteBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InsertNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertNoteBinding
    private lateinit var db: SQLiteDatabase
    private lateinit var rs: Cursor
    private var noteID: String? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInsertNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val helper = MyHelper(this)
        db = helper.writableDatabase

        AddEventBtnBack()
        AddEcentBtnInsertNote()
        StringExtraFromList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun AddEcentBtnInsertNote() {
        binding.btnInsertNote.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val content = binding.edtInsertNoteContent.text.toString()
            val dateNote: String = getCurrentDate()

            if (title == "" || content == "") {
                Toast.makeText(this, "Hãy điền đầy đủ nội dung của note", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val dialog = AlertDialog.Builder(this)
                dialog.apply {
                    setTitle("Confirm")
                    setMessage("Do you want to create a note?")
                    setIcon(R.drawable.ic_confirm)

                    setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                        val query = "INSERT INTO TODOLIST(NOTE, CONTENT, DATETIME) VALUES(?, ?, ?)"
                        db.execSQL(query, arrayOf(title, content, dateNote))
                        finish()
                    }
                    setNegativeButton("No") { dialogIt: DialogInterface, _: Int ->
                        dialogIt.dismiss()
                    }
                }.show()
            }
        }
    }

    //getDateTime
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDate(): String {
        val current = LocalDateTime.now()
        val fm = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return current.format(fm)
    }

    private fun AddEventBtnBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    //sau khi dung activity nay thi cac ham trong onPause se thuc hien
    override fun onPause() {
        super.onPause()
        updateNoteInDatabase()
    }

    //fun nay dung de get value dc truyen tu activity truoc
    private fun StringExtraFromList() {
        noteID = intent.getStringExtra("_id")
        val title = intent.getStringExtra("Title")
        val content = intent.getStringExtra("Content")

        binding.edtTitle.setText(title)
        binding.edtInsertNoteContent.setText(content)
    }

    //fun update db neu co su thay doi
    private fun updateNoteInDatabase() {
        //neu co xuat hien id duoc truyen tu activity truoc
        //thi se thuc hien thay doi db khi nguoi dung coi note va sua note (update)
        if (noteID != null) {
            val updateTitle = binding.edtTitle.text.toString()
            val updateContent = binding.edtInsertNoteContent.text.toString()

            //tao bien contentValue dong vay tro vat chua
            //de luu tru note va content moi de update value tai _id
            val contentValue = ContentValues().apply {
                put("NOTE", updateTitle)
                put("CONTENT", updateContent)
            }

            db.update("TODOLIST", contentValue, "_id = ?", arrayOf(noteID))
        }
    }


}