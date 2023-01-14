package com.example.jetpackchatapp.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackchatapp.model.data.MainViewModel
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.ui.screens.*


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    navFrameController: NavHostController? = null,
    isFrame: Boolean = false,
    mainViewModel: MainViewModel,
    isLogged: Boolean = false
) {
    NavHost(
        navController = navController,
        startDestination = if (!isFrame) Screen.Chats.route else if (isLogged) Screen.Main.route else Screen.OnBoarding.route
    ) {
        composable(route = Screen.Chats.route) {
            ChatsScreen(navFrameController!!, mainViewModel)
        }
        composable(route = Screen.Contacts.route) {
            ContactsScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navFrameController!!, mainViewModel)
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navController, mainViewModel)
        }
        composable(route = Screen.Main.route) {
            if (navFrameController != null) MainScreen(navFrameController, mainViewModel)
        }
        composable(route = Screen.SignUp.route) {
            CreateAccountScreen(navController, mainViewModel)
        }
        composable(route = Screen.Account.route) {
            AccountSettingsScreen(navFrameController!!)
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(mainViewModel, navFrameController!!)
        }
        composable(route = Screen.ChatDetails.route) {
            ChatDetailsScreen(mainViewModel, navFrameController!!)
        }
        composable(route = Screen.AddChats.route) {
            AddChatScreen(navFrameController!!, mainViewModel)
        }
    }
}
