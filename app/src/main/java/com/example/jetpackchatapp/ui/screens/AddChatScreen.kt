package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.repository.addChat
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatButton
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.EditText

@Composable
fun AddChatScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val valueViewModel = MainViewModel()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Purple
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = imageData[7]),
                        contentDescription = null,
                        modifier = Modifier
                            .width(28.dp)
                            .height(30.dp)
                    )
                }
                ChatText(text = titleData[6], fontFamily = boldFont, size = 24.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            EditText(mainViewModel = valueViewModel, hint = "Email", isPassword = false) {}
            Spacer(modifier = Modifier.height(30.dp))
            ChatButton(text = "Add", padding_start = 0.dp) {
                mainViewModel.addChat(valueViewModel.value.toString(),object : Callback {
                    override fun call(T: Any?) {
                        mainViewModel.setActiveChat(T as ChatModel, object: Callback{
                            override fun call(T: Any?) {
                                navController.navigate(Screen.ChatDetails.route)
                            }
                        })
                    }
                })
            }
        }
    }
}