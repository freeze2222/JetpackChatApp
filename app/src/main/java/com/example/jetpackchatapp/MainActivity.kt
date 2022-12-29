package com.example.jetpackchatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.example.jetpackchatapp.model.data.MainViewModel
import com.example.jetpackchatapp.ui.screens.*
import com.example.jetpackchatapp.ui.theme.JetpackChatAppTheme
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.database.setPersistenceEnabled(true)

        setContent {
            val viewModel : MainViewModel by  viewModels()
            JetpackChatAppTheme {
                FrameScreen(viewModel)
            }
        }
    }
    override fun onBackPressed() {}
    }


