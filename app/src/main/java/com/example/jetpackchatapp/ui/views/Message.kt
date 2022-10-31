package com.example.jetpackchatapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.jetpackchatapp.model.data.Message
import com.example.jetpackchatapp.model.data.semiBoldFont
import com.example.jetpackchatapp.ui.theme.Coral
import com.example.jetpackchatapp.ui.theme.DarkViolet
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Message(data: Message) {
    val isFromMe =
        data.from_user!="Test1"//FirebaseAuth.getInstance().currentUser!!.displayName == data.from_user
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isFromMe) Coral else DarkViolet),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(48.dp)),
            horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChatText(text = data.text, fontFamily = semiBoldFont)
        }
    }
}