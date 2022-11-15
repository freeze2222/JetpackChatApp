package com.example.jetpackchatapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackchatapp.controller.SetupNavFrame
import com.example.jetpackchatapp.controller.SetupNavGraph

@Composable
fun FrameScreen() {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController,navController, true)
}