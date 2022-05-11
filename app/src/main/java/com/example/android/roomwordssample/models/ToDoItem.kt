package com.example.android.roomwordssample.models

data class ToDoItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)