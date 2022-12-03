package com.example.jetpackchatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackchatapp.R
import com.example.jetpackchatapp.model.data.Callback
import com.example.jetpackchatapp.model.data.boldFont
import com.example.jetpackchatapp.model.data.titleData
import com.example.jetpackchatapp.repository.getChatsListData
import com.example.jetpackchatapp.repository.getContactListData
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.example.jetpackchatapp.ui.theme.Purple
import com.example.jetpackchatapp.ui.views.ChatText
import com.example.jetpackchatapp.ui.views.User

@Composable
fun ContactsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Purple
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple)
        ) {
            Spacer(modifier = Modifier.height(46.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                ChatText(text = titleData[3], fontFamily = boldFont)
                Spacer(modifier = Modifier.weight(1F))
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.plus_icon),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp))
                    .fillMaxSize()
                    .background(LightPurple),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(46.dp))
                val items = getContactListData("test1", object:Callback{
                    override fun call(T: Any?) {

                    }

                })
                Spacer(modifier = Modifier.width(15.dp))
                LazyColumn {
                    //items(items = items) { item ->
                    //    User(data = item)
                    //}
                }
            }
        }
        Spacer(modifier = Modifier.height(49.dp))
    }
}

@Preview
@Composable
fun ContactsScreenPreview() {
    ContactsScreen()
}