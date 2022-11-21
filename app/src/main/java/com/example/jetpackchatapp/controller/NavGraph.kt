package com.example.jetpackchatapp.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.data.ViewModel
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.ui.screens.*


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    navFrameController: NavHostController? = null,
    isFrame: Boolean = false,
    viewModel: ViewModel,
    isLogged: Boolean = false
) {
    NavHost(
        navController = navController,
        startDestination = if (!isFrame) Screen.Chats.route else if (isLogged) Screen.Main.route else Screen.OnBoarding.route
    ) {
        composable(route = Screen.Chats.route) {
            ChatsScreen(navFrameController!!, viewModel)
        }
        composable(route = Screen.Contacts.route) {
            ContactsScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navFrameController!!)
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navController, viewModel)
        }
        composable(route = Screen.Main.route) {
            if (navFrameController != null) MainScreen(navFrameController, viewModel)
        }
        composable(route = Screen.SignUp.route) {
            CreateAccountScreen(navController)
        }
        composable(route = Screen.Account.route) {
            AccountSettingsScreen(navFrameController!!)
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(viewModel, navFrameController!!)
        }
        composable(route = Screen.ChatDetails.route) {
            ChatDetailsScreen(viewModel, navFrameController!!)
        }
    }
}
