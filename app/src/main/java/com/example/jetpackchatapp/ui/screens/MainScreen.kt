package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackchatapp.controller.SetupNavGraph
import com.example.jetpackchatapp.model.data.Callback
import com.example.jetpackchatapp.model.data.MainViewModel
import com.example.jetpackchatapp.repository.getName
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.views.BottomNavigationBar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainScreen(navFrameController: NavHostController, mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {},
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {

                mainViewModel.currentUser = FirebaseAuth.getInstance().currentUser!!
                getName(callback = object : Callback{
                    override fun call(T: Any?) {
                        mainViewModel.name = T.toString()
                    }

                })
                SetupNavGraph(
                    navController = navController,
                    navFrameController,
                    false,
                    mainViewModel
                )
            }
        },
        backgroundColor = LightPurple
    )
}
