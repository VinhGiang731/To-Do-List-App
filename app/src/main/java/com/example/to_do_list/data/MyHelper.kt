package com.example.to_do_list.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context) : SQLiteOpenHelper(context, "TODOLIST_DB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE TODOLIST(_id INTEGER PRIMARY KEY AUTOINCREMENT, NOTE TEXT, DATETIME TEXT)")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, DATETIME) VALUES('cái 1', '31/07/2004')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, DATETIME) VALUES('cái 2', '31/07/2005')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, DATETIME) VALUES('cái 3 note with me', '31/07/2006')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, DATETIME) VALUES('cái 4 note with me', '31/07/2007')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, DATETIME) VALUES('cái5 note with me', '31/07/2008')")
        db?.execSQL("INSERT INTO TODOLIST(NOTE, DATETIME) VALUES('cái6 note with me', '31/07/2009')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}