package com.example.jetpackchatapp.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.ui.screens.ChatsScreen
import com.example.jetpackchatapp.ui.screens.ContactsScreen
import com.example.jetpackchatapp.ui.screens.ProfileScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Chats.route) {
        composable(route = Screen.Chats.route){
            ChatsScreen()
        }
        composable(route = Screen.Contacts.route){
            ContactsScreen()
        }
        composable(route = Screen.Profile.route){
            ProfileScreen()
        }
    }
}