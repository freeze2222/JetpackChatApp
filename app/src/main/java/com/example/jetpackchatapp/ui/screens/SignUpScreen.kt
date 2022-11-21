package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.model.navigation.Screen
import com.example.jetpackchatapp.repository.createAccount
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.*

@Composable
fun CreateAccountScreen(navController: NavController) {
    val usernameViewModel = ViewModel()
    val passwordViewModel = ViewModel()
    val email = ViewModel()
    val passwordConfirmationViewModel = ViewModel()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(id = imageData[6]),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(88.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .background(LightPurple)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .fillMaxWidth()
                        .height(30.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Image(
                            painter = painterResource(id = imageData[7]),
                            contentDescription = null,
                            modifier = Modifier
                                .width(28.dp)
                                .height(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    ChatText(
                        text = titleData[1],
                        fontFamily = boldFont,
                        size = 20.sp,
                        padding_start = 0.dp
                    )

                }
                Spacer(modifier = Modifier.height(20.dp))
                ChatText(text = descriptionData[0], fontFamily = regularFont, size = 18.sp)
                Spacer(modifier = Modifier.height(12.dp))
                EditText(hint = descriptionData[7], isPassword = false, viewModel = usernameViewModel) {
                    Image(
                        painter = painterResource(id = imageData[3]),
                        contentDescription = null,
                        modifier = Modifier
                            .width(17.dp)
                            .height(19.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Separator()
                Spacer(modifier = Modifier.height(16.dp))
                ChatText(text = descriptionData[5], fontFamily = regularFont, size = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                EditText(hint = descriptionData[6], isPassword = false, viewModel = email) {
                    TextImageView(id = imageData[5])
                }
                Spacer(modifier = Modifier.height(18.dp))
                Separator()
                Spacer(modifier = Modifier.height(16.dp))
                ChatText(text = descriptionData[1], fontFamily = regularFont, size = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                EditText(
                    hint = descriptionData[2],
                    isPassword = true,
                    viewModel = passwordViewModel
                ) {
                    TextImageView(id = imageData[4])
                }
                Spacer(modifier = Modifier.height(18.dp))
                Separator()
                Spacer(modifier = Modifier.height(16.dp))
                ChatText(text = descriptionData[8], fontFamily = regularFont, size = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                EditText(
                    hint = descriptionData[2],
                    isPassword = true,
                    viewModel = passwordConfirmationViewModel
                ) {
                    TextImageView(id = imageData[4])
                }
                Spacer(modifier = Modifier.height(20.dp))
                ChatButton(text = descriptionData[4]) {
                    createAccount(navController)
                }
            }
        }
    }
}