package com.example.jetpackchatapp.ui.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackchatapp.model.data.imageData
import com.example.jetpackchatapp.ui.theme.Purple

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .width(292.dp)
            .height(278.dp)
            .background(Purple)
    ) {
        Image(
            painter = painterResource(id = imageData[0]),
            contentDescription = null,
            modifier = Modifier
                .width(292.dp)
                .height(278.dp)
        )
    }
}