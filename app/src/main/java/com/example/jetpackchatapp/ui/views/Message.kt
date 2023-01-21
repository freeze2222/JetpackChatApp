package com.example.jetpackchatapp.ui.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.data.regularFont
import com.example.jetpackchatapp.ui.theme.Coral
import com.example.jetpackchatapp.ui.theme.DarkViolet
import com.example.jetpackchatapp.ui.theme.LightPurple
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Message(data: MessageModel) {
    Log.e("Debug",data.from_user)
    val isFromMe = FirebaseAuth.getInstance().currentUser!!.displayName == data.from_user
    Log.e("Debug", isFromMe.toString())
    Log.e("Debug", FirebaseAuth.getInstance().currentUser!!.toString())
    val color = if (isFromMe) Coral else DarkViolet
    Surface(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .background(LightPurple),
            horizontalArrangement = if (!isFromMe) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(48.dp))
                    .background(color)
                    .defaultMinSize(minHeight = 45.dp)
                    .widthIn(0.dp, 300.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ChatText(
                    text = data.text,
                    fontFamily = regularFont,
                    size = 23.sp,
                    padding_start = 25.dp,
                    padding_end = 25.dp
                )
            }
        }

    }
}