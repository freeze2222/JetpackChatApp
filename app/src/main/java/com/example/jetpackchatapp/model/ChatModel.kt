package com.example.jetpackchatapp.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

data class ChatModel(
    val name: String = "",
    val last_message: String = "",
    val firstUID: Long = 0,
    val secondUID: Long = 0,
    val chatUID: Long = 0,
    val avatarRef: String = "null",
    val new_messages: Int = 0,
    val lastSeen: Long = Calendar.getInstance().timeInMillis
)
