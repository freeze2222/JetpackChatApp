package com.example.jetpackchatapp.repository

import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.Message


fun getMessagesListData(): List<Message> {
    return listOf(
        Message(text = "Test1", "Freeze2222"),
        Message(text = "Test2", "Test2")
    )

}