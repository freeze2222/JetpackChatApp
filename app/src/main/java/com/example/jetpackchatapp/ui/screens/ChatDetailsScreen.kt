package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.model.data.imageData
import com.example.jetpackchatapp.repository.getMessagesListData
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.Message

@Composable
fun ChatDetailsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple)
        ) {
            Spacer(modifier = Modifier.height(26.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = imageData[7]),
                        contentDescription = null,
                        modifier = Modifier
                            .width(28.dp)
                            .height(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(17.dp))
                Image(
                    painter = painterResource(id = R.drawable.avatar_image),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .width(58.dp)
                        .height(58.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier
                        .height(45.dp)
                        .background(Purple)
                ) {
                    ChatText(
                        "Enes Zenler",
                        fontFamily = boldFont,
                        size = 18.sp,
                        padding_start = 0.dp
                    )
                    ChatText("Online", size = 16.sp, padding_start = 0.dp)
                }
                Spacer(modifier = Modifier.width(90.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_more_options),
                        contentDescription = null,
                        modifier = Modifier
                            .width(38.dp)
                            .height(38.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .background(
                        LightPurple
                    )
            ) {
                val items = getMessagesListData()
                LazyColumn{
                    items(items = items){item ->
                        Message(data = item)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ChatDetailsScreenPreview() {
    ChatDetailsScreen()
}