package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.model.data.titleData
import com.example.jetpackchatapp.repository.getChatsListData
import com.example.jetpackchatapp.repository.getContactListData
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.Chat
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.User

@Composable
fun ChatsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            ChatText(text = titleData[2], fontFamily = boldFont, size = 24.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .fillMaxSize()
                    .background(LightPurple),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(46.dp))
                val items = getChatsListData()
                Spacer(modifier = Modifier.width(15.dp))
                LazyColumn {
                    items(items = items) { item ->
                        Chat(data = item)
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Purple)
            ) {
                Spacer(modifier = Modifier.width(63.dp))
            }
        }
    }
}

@Preview
@Composable
fun ChatsScreenPreview() {
    ChatsScreen()
}