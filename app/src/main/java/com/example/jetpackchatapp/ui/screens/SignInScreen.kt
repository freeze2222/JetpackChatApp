package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.descriptionData
import com.example.jetpackchatapp.model.data.imageData
import com.example.jetpackchatapp.model.data.titleData
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.EditText

@Composable
fun SignInScreen() {
    Surface(
        modifier = Modifier
            .background(Purple)
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .background(Purple)
                .padding(start = 18.dp)
                .width(206.dp)
                .height(135.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageData[1]),
                contentDescription = null,
                modifier = Modifier
                    .width(206.dp)
                    .height(135.dp)
            )

            Spacer(modifier = Modifier.height(56.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ChatText(text = titleData[0])
                }
                Spacer(modifier = Modifier.height(75.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    ChatText(text = descriptionData[0])
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    EditText(hint = "freeze2222", isPassword = false)
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