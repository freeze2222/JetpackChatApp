package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.model.data.titleData
import com.example.jetpackchatapp.repository.getUsersListData
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.Message
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
                val items = getUsersListData()
                LazyColumn {
                    items(items = items) { item ->
                        User(data = item)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChatsScreenPreview() {
    ChatsScreen()
}