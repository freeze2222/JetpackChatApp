package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.repository.changeCredentials
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.*

@Composable
fun AccountSettingsScreen(controller: NavController) {
    val usernameMainViewModel = MainViewModel()
    val passwordMainViewModel = MainViewModel()
    val passwordConfirmationMainViewModel = MainViewModel()
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Purple
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple)
        )
        {
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                IconButton(onClick = {
                    controller.navigate(Screen.Main.route) {
                        controller.popBackStack()
                    }
                }) {
                    Image(
                        painter = painterResource(id = imageData[7]),
                        contentDescription = null,
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                    )
                }
                ChatText(text = titleData[5], fontFamily = boldFont)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .fillMaxSize()
                    .background(LightPurple),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .width(125.dp), contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.no_avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(
                                CircleShape
                            )
                            .width(125.dp)
                            .height(125.dp)
                            .border(4.dp, Color.Black, shape = CircleShape)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icon_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .alpha(0.7F)
                    )
                }
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                    Spacer(modifier = Modifier.height(20.dp))
                    ChatText(text = descriptionData[0])
                    Spacer(modifier = Modifier.height(8.dp))

                    EditText(
                        hint = descriptionData[7],
                        isPassword = false,
                        mainViewModel = usernameMainViewModel
                    ) {
                        TextImageView(id = imageData[3])
                    }
                    Spacer(modifier = Modifier.height(21.dp))
                    Separator()
                    Spacer(modifier = Modifier.height(13.dp))
                    ChatText(text = descriptionData[1])
                    Spacer(modifier = Modifier.height(8.dp))
                    EditText(
                        hint = descriptionData[2],
                        isPassword = true,
                        mainViewModel = passwordMainViewModel
                    ) {
                        TextImageView(id = imageData[4])
                    }
                    Spacer(modifier = Modifier.height(29.dp))
                    Separator()
                    Spacer(modifier = Modifier.height(21.dp))
                    ChatText(text = descriptionData[14])
                    Spacer(modifier = Modifier.height(8.dp))
                    EditText(
                        hint = descriptionData[2], isPassword = true,
                        mainViewModel = passwordConfirmationMainViewModel
                    ) {
                        TextImageView(id = imageData[4])
                    }
                    Spacer(modifier = Modifier.height(26.dp))
                    ChatButton(text = descriptionData[13]) {
                        val username = usernameMainViewModel.value.toString()
                        val password = passwordMainViewModel.value.toString()
                        val passwordConfirmation =
                            passwordConfirmationMainViewModel.value.toString()
                        changeCredentials(
                            username,
                            password,
                            passwordConfirmation,
                            context,
                            controller
                        )
                    }
                }
            }
        }
    }
}
