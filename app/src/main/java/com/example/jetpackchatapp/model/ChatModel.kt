package com.example.jetpackchatapp.model

data class ChatModel(
    val name: String,
    val last_message: String,
    val firstUID : String,
    val secondUID: String,
    val ChatUID: String,
    val time: String,
    val avatar: Int?,
    val new_messages: Int,
    val lastSeen: String

)
