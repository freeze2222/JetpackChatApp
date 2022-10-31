package com.example.jetpackchatapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackchatapp.model.data.imageData

@Composable
fun Separator() {
    Image(
        painter = painterResource(id = imageData[2]),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    )
}