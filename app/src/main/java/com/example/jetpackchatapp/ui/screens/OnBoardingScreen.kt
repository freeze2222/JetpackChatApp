package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpackchatapp.controller.SetupNavGraph
import com.example.jetpackchatapp.model.navigation.Screen

@Composable
fun OnBoardingScreen() {
        Scaffold {
            val controller = rememberNavController()
            SetupNavGraph(navController = controller,)
            controller.navigate(Screen.SignIn.route){
                popUpTo(0) {}
            }
        }
}