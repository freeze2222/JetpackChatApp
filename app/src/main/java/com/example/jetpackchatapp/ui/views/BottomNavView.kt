package com.example.jetpackchatapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.ui.theme.LightPurple

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Chats,
        Screen.Contacts,
        Screen.Profile
    )
    BottomNavigation(
        modifier = Modifier.graphicsLayer {
            shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)
            clip = true
        },
        backgroundColor = Purple,
        contentColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon!!),
                        contentDescription = item.title,
                        modifier = Modifier
                            .padding(bottom = 5.dp, top = 10.dp)
                            .size(20.dp)
                    )
                },
                label = { Text(text = item.title!!) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
