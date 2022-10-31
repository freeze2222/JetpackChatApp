package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatButton
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.EditText
import com.example.jetpackchatapp.ui.views.Separator

@Composable
fun SignInScreen() {
    Surface(
        modifier = Modifier
            .background(Purple)
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = imageData[1]),
                contentDescription = null,
                modifier = Modifier
                    .width(206.dp)
                    .height(135.dp)
            )
            Spacer(modifier = Modifier.height(36.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .background(LightPurple)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(33.dp))
                    ChatText(text = titleData[0], fontFamily = boldFont, size = 20.sp)
                    Spacer(modifier = Modifier.height(35.dp))
                    ChatText(text = descriptionData[0], fontFamily = regularFont, size = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    EditText(hint = descriptionData[7], isPassword = false) {
                        Image(
                            painter = painterResource(id = imageData[3]),
                            contentDescription = null,
                            modifier = Modifier
                                .width(17.dp)
                                .height(19.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(21.dp))
                    Separator()
                    Spacer(modifier = Modifier.height(21.dp))
                    ChatText(text = descriptionData[1], fontFamily = regularFont, size = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    EditText(hint = descriptionData[2], isPassword = true) {
                        Image(
                            painter = painterResource(id = imageData[4]),
                            contentDescription = null,
                            modifier = Modifier
                                .width(18.dp)
                                .height(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(35.dp))
                    ChatButton(text = descriptionData[3]) { TODO() }
                    Spacer(modifier = Modifier.height(16.dp))
                    ChatButton(text = descriptionData[4]) { TODO() }
                }


            }
        }
    }
}


@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}