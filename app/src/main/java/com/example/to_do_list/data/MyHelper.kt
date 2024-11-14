package com.example.to_do_list.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context : Context) :SQLiteOpenHelper(context, "TODOLIST_DB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE TODOLIST(DATETIME TEXT, NOTE TEXT)")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'First note with me')")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'Second note with me')")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'Second note with me')")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'Second note with me')")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'Second note with me')")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'Second note with me')")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'Second note with me')")
        db?.execSQL("INSERT INTO TODOLIST(DATETIME, NOTE) VALUES('31/07/2004', 'Testtttttttttttttttt')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}