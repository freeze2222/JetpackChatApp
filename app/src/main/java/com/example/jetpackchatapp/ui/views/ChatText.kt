package com.example.jetpackchatapp.ui.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.regularFont

@Composable
fun ChatText(
    text : String = "",
    color: Color = Color.White,
    size: TextUnit = 24.sp,
    fontFamily:FontFamily = regularFont
) {
    Text(text = text, color = color, fontSize = size)
}