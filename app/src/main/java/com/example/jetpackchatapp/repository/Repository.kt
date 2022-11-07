package com.example.jetpackchatapp.repository

import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.UserModel


fun getMessagesListData(): List<MessageModel> {
    return listOf(
        MessageModel(text = "TestFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        MessageModel(text = "TestFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        MessageModel(text = "TestFFFFFFFFFFFFF", "Test1"),
        MessageModel(text = "TestFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        MessageModel(text = "Test1", "Test1")
    )

}

fun getChatsListData(): List<ChatModel> {
    return listOf(
        ChatModel("Freeze2222", "...", "#000000", "23:51", null, 3, "23:52"),
        ChatModel("NotFreeze2222", "...", "#000001", "23:00", null, 0, "23:00"),
        ChatModel("AbsolutelyNotFreeze2222", "test-1", "#000002", "0:00", null, 125, "0:00")

    )
}

fun getContactListData(): List<UserModel>{
    return listOf(
        UserModel("User1","Test1","#0000",null,"00:12")
    )
}