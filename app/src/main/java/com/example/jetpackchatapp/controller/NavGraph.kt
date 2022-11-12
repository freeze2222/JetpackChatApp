package com.example.jetpackchatapp.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.ui.screens.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Chats.route) {
        composable(route = Screen.Chats.route) {
            ChatsScreen()
        }
        composable(route = Screen.Contacts.route) {
            ContactsScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen()
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
    }
}