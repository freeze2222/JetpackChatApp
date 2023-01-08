package com.example.jetpackchatapp.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

data class UserModel(
    val name: String = "",
    val description: String = "",
    val UID: Long = UUID.randomUUID().mostSignificantBits,
    val avatarRef:String = "null", //TODO
    val lastSeen: Long = 12.toLong(),
    val contacts: List<UserModel> = listOf(),
    val chats: List<ChatModel> = listOf(),
    )
