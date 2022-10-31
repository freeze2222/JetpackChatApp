package com.example.jetpackchatapp.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.ui.theme.Orange

@Composable
fun ChatButton(
    text: String,
    padding_start: Dp = 85.dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(start = padding_start)
            .clip(RoundedCornerShape(48.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(48.dp))
            .width(205.dp)
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontFamily = boldFont,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}