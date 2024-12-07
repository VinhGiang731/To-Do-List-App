package com.example.to_do_list.model

data class ListSchedule(
    val day: String,
    val title: String,
    val fullday: Int,
    val timeStart: String,
    val timeEnd: String,
    val place: String,
    val notes: String
)
