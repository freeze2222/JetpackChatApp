package com.example.jetpackchatapp.ui.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.model.data.regularFont

@Composable
fun ChatText(
    text: String = "",
    color: Color = Color.White,
    size: TextUnit = 24.sp,
    padding_start: Dp = 38.dp,
    padding_end: Dp = 0.dp,
    fontFamily: FontFamily = regularFont,
) {
    Text(
        text = text,
        color = color,
        fontSize = size,
        fontFamily = fontFamily,
        modifier = Modifier.padding(start = padding_start, end = padding_end),
    )
}