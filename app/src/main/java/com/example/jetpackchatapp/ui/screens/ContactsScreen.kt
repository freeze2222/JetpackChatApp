package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackchatapp.ui.theme.Purple

@Composable
fun ContactsScreen() {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Purple
                ) {
                    Text(text = "Test")
                }
}

@Preview
@Composable
fun ContactsScreenPreview() {
    ContactsScreen()
}