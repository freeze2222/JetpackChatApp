@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.jetpackchatapp.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditText(hint: String,
             isPassword: Boolean,
             padding_start: Dp = 18.dp,
             trailing_icon: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember {
            mutableStateOf("")
        }
        var focusState by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = White,
                textColor = Gray,
                focusedLabelColor = Color.Transparent,
                focusedBorderColor = Black,
                unfocusedBorderColor = Black,
            ),
            shape = RoundedCornerShape(48.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = {
                Text(
                    text = if (focusState) "" else hint,
                    color = Gray,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .padding(start = padding_start)
                .height(45.dp)
                .width(327.dp)
                .onFocusChanged { focus ->
                    focusState = focus.isFocused
                },
            trailingIcon = trailing_icon
        )
    }
}