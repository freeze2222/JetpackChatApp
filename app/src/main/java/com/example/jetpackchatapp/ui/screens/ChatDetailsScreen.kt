package com.example.jetpackchatapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.repository.*
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.EditText
import com.example.jetpackchatapp.ui.views.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import androidx.compose.runtime.getValue


@Composable
fun ChatDetailsScreen(data: MainViewModel, navController: NavController) {
    val chatModel = data.chatModel
    var messagesList = remember {
        mutableStateListOf<MessageModel>()
    }
    messagesList.addAll(data.messageList)
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(26.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
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
                    painter = if (chatModel.avatarRef != FirebaseDatabase.getInstance().getReference(EMPTY_REFERENCE)
                    ) {
                        painterResource(getAvatar(chatModel.avatarRef))
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
                    /*
                    ChatText(
                        text = if (isUserOnline(chatModel)) "Online" else "Offline",
                        size = 16.sp,
                        padding_start = 0.dp
                    )
                     */
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
                    .weight(6f)
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .background(
                        LightPurple
                    )
            ) {
                Spacer(modifier = Modifier.height(45.dp))

                LazyColumn {
                    items(items = data.testMutableList) { item ->
                        Message(data = item)
                    }
                }

            }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .weight(1f, false)

                ) {
                    EditText(
                        hint = descriptionData[9],
                        isPassword = false,
                        hint_size = 18.sp,
                        width = 270.dp,
                        height = 57.dp,
                        mainViewModel = data
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
                                .clickable {
                                    getUsername(
                                        (FirebaseAuth.getInstance().currentUser!!.email!!).toString(),
                                        object : Callback {
                                            override fun call(T: Any?) {
                                                val message = parseMessage(
                                                    data.value.toString(),
                                                    T as String
                                                )
                                                sendMessage(message, chatModel.chatUID)

                                            }
                                        })
                                }
                        )
                    }
                }
            }
        }
    }
