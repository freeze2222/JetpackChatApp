package com.example.jetpackchatapp.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.ui.screens.*


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    navFrameController: NavHostController? = null,
    isFrame: Boolean = false
) {
    NavHost(
        navController = navController,
        startDestination = if (!isFrame) Screen.Chats.route else Screen.Main.route
    ) {
        composable(route = Screen.Chats.route) {
            ChatsScreen()
        }
        composable(route = Screen.Contacts.route) {
            ContactsScreen()
        }
        composable(route = Screen.Profile.route) {
            //if (navFrameController != null) {
            ProfileScreen(navFrameController!!)
            //}
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(navFrameController)
        }
        composable(route = Screen.SignUp.route) {
            CreateAccountScreen(navController)
        }
        composable(route = Screen.Account.route) {
            AccountSettingsScreen()
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen()
        }
        composable(route = Screen.MainFrame.route) {
            FrameScreen()
        }
    }
}

@Composable
fun SetupNavFrame(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.MainFrame.route) {
            FrameScreen()
        }
        composable(route = Screen.MainFrame.route) {
            ProfileScreen(navController = navController)
        }
    }
}