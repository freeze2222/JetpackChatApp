package com.example.jetpackchatapp.model

import androidx.compose.ui.graphics.painter.Painter
import java.util.*

data class UserModel(
    val name: String,
    val description: String,
    val UID: UUID,
    val avatar: Painter?,
    val lastSeen: Long,

    )
