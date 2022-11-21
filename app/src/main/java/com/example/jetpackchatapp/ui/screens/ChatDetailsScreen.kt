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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.ViewModel
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.model.data.descriptionData
import com.example.jetpackchatapp.model.data.imageData
import com.example.jetpackchatapp.repository.getMessagesListData
import com.example.jetpackchatapp.repository.isUserOnline
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.EditText
import com.example.jetpackchatapp.ui.views.Message

@Composable
fun ChatDetailsScreen(data: ViewModel, navController: NavController) {
    val messageViewModel = ViewModel()
    var text : String
    val chatModel = data.chatModel!!
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
                IconButton(onClick = {navController.popBackStack()}) {
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
                    painter = if (chatModel.avatar != null) {
                        painterResource(chatModel.avatar)
                    } else {
                        painterResource(
                            id = R.drawable.no_avatar
                        )
                    },
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
                        chatModel.name,
                        fontFamily = boldFont,
                        size = 18.sp,
                        padding_start = 0.dp
                    )
                    ChatText(
                        text = if (isUserOnline(chatModel)) "Online" else "Offline",
                        size = 16.sp,
                        padding_start = 0.dp
                    )
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
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .background(
                        LightPurple
                    )
            ) {
                Spacer(modifier = Modifier.height(45.dp))
                val items = getMessagesListData(chatModel)
                LazyColumn {
                    items(items = items) { item ->
                        Message(data = item)
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    EditText(
                        hint = descriptionData[9],
                        isPassword = false,
                        hint_size = 18.sp,
                        width = 270.dp,
                        height = 57.dp,
                        viewModel = messageViewModel
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .clip(
                                CircleShape
                            )
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = imageData[8]),
                            contentDescription = null,
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
    }
}