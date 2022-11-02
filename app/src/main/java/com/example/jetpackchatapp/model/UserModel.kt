package com.example.jetpackchatapp.model

import androidx.compose.ui.graphics.painter.Painter

data class UserModel(
    val name: String, //19 symbols !!! TODO
    val description: String,
    val UID: String,
    val avatar: Painter?,
    val lastSeen: String,

)
