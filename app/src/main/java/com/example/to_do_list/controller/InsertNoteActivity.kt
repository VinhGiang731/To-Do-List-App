package com.example.to_do_list.controller

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.data.MyHelper
import com.example.to_do_list.databinding.ActivityInsertNoteBinding

class InsertNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertNoteBinding
    private lateinit var db: SQLiteDatabase
    private var noteID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInsertNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val helper = MyHelper(this)
        db = helper.writableDatabase

        AddEventBtnBack()
        StringExtraFromList()
    }

    //fun nay dung de get value dc truyen tu activity truoc
    private fun StringExtraFromList() {
        noteID = intent.getStringExtra("_id")
        val title = intent.getStringExtra("Title")
        val content = intent.getStringExtra("Content")

        binding.edtTitle.setText(title)
        binding.edtInsertNoteContent.setText(content)
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

    //fun update db neu co su thay doi
    private fun updateNoteInDatabase() {
        //neu co xuat hien id duoc truyen tu activity truoc
        //thi se thuc hien thay doi db khi nguoi dung coi note va sua note (update)
        if(noteID != null){
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