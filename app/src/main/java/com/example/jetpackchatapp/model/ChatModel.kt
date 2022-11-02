package com.example.jetpackchatapp.model

import androidx.compose.ui.graphics.painter.Painter

data class ChatModel (
    val name: String,
    val last_message: String,
    val UID : String,
    val time: String,
    val avatar: Painter?,
    val new_messages:Int,
    val lastSeen: String

)
