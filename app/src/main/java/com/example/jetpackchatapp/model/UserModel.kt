package com.example.jetpackchatapp.model

import androidx.compose.ui.graphics.painter.Painter
import java.util.*

data class UserModel(
    val name: String = "",
    val description: String = "",
    val UID: UUID = UUID.randomUUID(),
    val avatar: Painter? = null,
    val lastSeen: Long = 12.toLong(),
    val contacts: List<UserModel> = listOf(),
    val chats : List<ChatModel> = listOf(),
    )
