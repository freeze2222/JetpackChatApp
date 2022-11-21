package com.example.jetpackchatapp.model

data class ChatModel(
    val name: String,
    val last_message: String,
    val receiverUID: String,
    val ChatUID: String,
    val time: String,
    val avatar: Int?,
    val new_messages: Int,
    val lastSeen: String

)
