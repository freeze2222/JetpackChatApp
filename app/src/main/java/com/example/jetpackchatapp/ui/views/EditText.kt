package com.example.jetpackchatapp.ui.views

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackchatapp.model.data.imageData
import com.example.jetpackchatapp.model.data.regularFont

@Composable
fun EditText(hint: String, isPassword: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember {
            mutableStateOf("")
        }
        var focusState by remember { mutableStateOf(false) }
        var visualTransformation by remember { mutableStateOf(if (isPassword) PasswordVisualTransformation() else VisualTransformation.None) }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = White,
                textColor = Gray,
                focusedLabelColor = Color.Transparent,
                focusedBorderColor = Yellow,
                unfocusedBorderColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(48.dp),
            placeholder = {
                Text(
                    text = if (focusState) "" else hint,
                    color = Gray,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .height(45.dp)
                .width(327.dp)
                .onFocusChanged { focus ->
                    focusState = focus.isFocused
                },
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = {
                        visualTransformation =
                            if (visualTransformation == PasswordVisualTransformation()) VisualTransformation.None else PasswordVisualTransformation()
                    }) {
                        Icon(
                            painter = painterResource(id = imageData[0]),
                            contentDescription = "Show password",
                            modifier = Modifier
                                .height(19.dp)
                                .width(17.dp),
                            tint = Color.Gray
                        )
                    }
                }
            } else {
                null
            },
            visualTransformation = visualTransformation
        )
    }
}