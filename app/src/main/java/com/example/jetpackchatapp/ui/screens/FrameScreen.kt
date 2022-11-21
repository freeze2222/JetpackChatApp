package com.example.jetpackchatapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackchatapp.controller.SetupNavGraph
import com.example.jetpackchatapp.model.data.ViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FrameScreen(viewModel: ViewModel) {
    val navController = rememberNavController()
    val isLogged = FirebaseAuth.getInstance().currentUser!=null
    SetupNavGraph(navController = navController, navFrameController = navController, true, viewModel,isLogged)
}