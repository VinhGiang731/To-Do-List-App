package com.example.to_do_list.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context) : SQLiteOpenHelper(context, "TODOLIST_DB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE TODOLIST(_id INTEGER PRIMARY KEY AUTOINCREMENT, NOTE TEXT, CONTENT TEXT, DATETIME TEXT)")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, CONTENT, DATETIME) VALUES('cái 1', 'Đây là nội dung note tui làm để test', '31/07/2004')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, CONTENT, DATETIME) VALUES('cái 2', 'Đây là nội dung note tui làm để test','31/07/2005')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, CONTENT, DATETIME) VALUES('cái 4', 'Đây là nội dung note tui làm để test', '31/07/2007')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, CONTENT, DATETIME) VALUES('cái 5', 'Đây là nội dung note tui làm để test', '31/07/2008')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, CONTENT, DATETIME) VALUES('cái 6', 'Đây là nội dung note tui làm để test', '31/07/2009')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }



}