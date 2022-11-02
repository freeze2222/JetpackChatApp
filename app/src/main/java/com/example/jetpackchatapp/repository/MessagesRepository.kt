package com.example.jetpackchatapp.repository

import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.Message


fun getMessagesListData(): List<Message> {
    return listOf(
        Message(text = "TestFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        Message(text = "TestFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        Message(text = "TestFFFFFFFFFFFFF", "Test1"),
        Message(text = "TestFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        Message(text = "Test1", "Test1")
    )

}