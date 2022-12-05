package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.data.Callback
import com.example.jetpackchatapp.model.data.ViewModel
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.model.data.titleData
import com.example.jetpackchatapp.repository.getChatsListData
import com.example.jetpackchatapp.repository.getContactListData
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.Chat
import com.example.jetpackchatapp.ui.views.ChatText
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ChatsScreen(navController:NavController, viewModel: ViewModel) {
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
                var value by remember {
                    mutableStateOf(listOf(ChatModel()))
                }

                getChatsListData(
                    (FirebaseAuth.getInstance().currentUser!!.email)!!.replace(
                        "@",
                        ""
                    ).replace(".", ""), object : Callback {
                        override fun call(T: Any?) {
                            val list = T as List<ChatModel>
                            value = list
                        }
                    })
                Spacer(modifier = Modifier.width(15.dp))
                LazyColumn {
                    items(items = value) { item ->
                        Chat(data = item, navController, viewModel = viewModel, value)
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
