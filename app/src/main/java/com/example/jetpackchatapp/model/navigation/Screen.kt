package com.example.jetpackchatapp.model.navigation

import com.example.jetpackchatapp.R

sealed class Screen(var route: String, var icon: Int, var title: String) {
    object Chats : Screen("chats", R.drawable.icon_message, "Chats")
    object Contacts : Screen("contacts", R.drawable.icon_users,"Contacts")
    object Profile : Screen("profile", R.drawable.icon_menu,"Profile")
}