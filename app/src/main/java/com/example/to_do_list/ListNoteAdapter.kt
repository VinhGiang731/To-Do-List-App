package com.example.to_do_list

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListNoteAdapter(var activity: Activity, val list: List<ListNote>) :
    ArrayAdapter<ListNote>(activity, R.layout.custom_itemnote) {

    override fun getCount(): Int {
        return list.size
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contexts = activity.layoutInflater
        val rowView = contexts.inflate(R.layout.custom_itemnote, parent, false)

        val txt_note = rowView.findViewById<TextView>(R.id.txt_note)
        val txt_note_date = rowView.findViewById<TextView>(R.id.txt_note_date)

        txt_note.text = list[position].txt_note
        txt_note_date.text = list[position].txt_noteDate

        return rowView
    }
}