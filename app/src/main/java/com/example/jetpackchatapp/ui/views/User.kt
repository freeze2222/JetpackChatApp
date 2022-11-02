package com.example.jetpackchatapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.model.data.imageData
import com.example.jetpackchatapp.repository.getUsersListData
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple

@Composable
fun User(data: ChatModel) {
    Surface(
        modifier = Modifier
            .padding(bottom = 11.dp)
            .fillMaxSize()
            .background(LightPurple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightPurple)
        ) {
            if (getUsersListData()[0].UID != data.UID) {
                Separator()
                Spacer(modifier = Modifier.height(11.dp))
            }
            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .height(60.dp)
                    .background(LightPurple), verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = imageData[9]),
                    contentDescription = null,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    Modifier
                        .background(LightPurple)
                        .sizeIn(maxWidth = 250.dp)
                ) {
                    ChatText(text = data.name, fontFamily = boldFont, size = 20.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    ChatText(text = data.last_message, size = 16.sp)
                }
                Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        Modifier
                            .padding(end = 15.dp)
                            .fillMaxSize()
                            .background(LightPurple),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.End
                    ) {
                        ChatText(text = data.time, size = 14.sp, padding_start = 0.dp)
                        Spacer(modifier = Modifier.height(15.dp))
                        if (data.new_messages!=0) {
                            Column(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Purple)
                                    .defaultMinSize(minHeight = 20.dp, minWidth = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                ChatText(
                                    text = data.new_messages.toString(),
                                    size = 12.sp,
                                    padding_start = 0.dp
                                )
                            }
                        }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserPreview() {
    User(data = getUsersListData()[0])
}