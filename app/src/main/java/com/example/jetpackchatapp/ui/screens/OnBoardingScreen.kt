package com.example.jetpackchatapp.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackchatapp.controller.SetupNavGraph
import com.example.jetpackchatapp.model.data.ViewModel
import com.example.jetpackchatapp.model.navigation.Screen

@Composable
fun OnBoardingScreen(viewModel: ViewModel, navFrameController:NavHostController) {
        Scaffold {
            val controller = rememberNavController()
            SetupNavGraph(navController = controller, viewModel = viewModel, navFrameController = navFrameController)
            controller.navigate(Screen.SignIn.route){
                controller.popBackStack()
            }
        }
}