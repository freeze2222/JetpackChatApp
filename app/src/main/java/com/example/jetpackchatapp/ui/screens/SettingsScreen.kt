package com.example.jetpackchatapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.Separator

@Composable
fun ProfileScreen() {
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
            Spacer(modifier = Modifier.height(49.dp))
            ChatText(text = titleData[4], fontFamily = boldFont)
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .fillMaxSize()
                    .background(LightPurple),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(78.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
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
                    Spacer(modifier = Modifier.height(4.dp))
                    ChatText(
                        text = "Username",
                        fontFamily = boldFont,
                        size = 24.sp,
                        padding_start = 0.dp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    ChatText(
                        text = descriptionData[10],
                        fontFamily = thinFont,
                        size = 16.sp,
                        padding_start = 0.dp
                    )
                }
                Spacer(modifier = Modifier.height(85.dp))
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = imageData[11]),
                        contentDescription = null,
                        modifier = Modifier
                            .height(32.dp)
                            .width(38.dp)
                    )
                    ChatText(text = descriptionData[11], fontFamily = boldFont, size = 20.sp)
                }
                Spacer(modifier = Modifier.height(26.dp))
                Separator()
                Spacer(modifier = Modifier.height(26.dp))
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .fillMaxWidth()
                        .clickable { Log.e("Deb","Click") }
                ) {
                    Image(
                        painter = painterResource(id = imageData[12]),
                        contentDescription = null,
                        modifier = Modifier
                            .height(32.dp)
                            .width(38.dp)
                    )
                    ChatText(text = descriptionData[12], fontFamily = boldFont, size = 20.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}