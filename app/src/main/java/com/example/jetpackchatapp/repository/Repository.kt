package com.example.jetpackchatapp.repository

import android.util.Log
import androidx.navigation.NavController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.data.EMAIL
import com.example.jetpackchatapp.model.data.PASSWORD
import com.example.jetpackchatapp.model.data.USERNAME
import com.example.jetpackchatapp.model.navigation.Screen
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import java.util.*


fun getMessagesListData(chatModel: ChatModel): List<MessageModel> {
    return listOf(
        MessageModel(
            text = "TestFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF",
            "Freeze2222"
        ),
        MessageModel(text = "TestFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        MessageModel(text = "TestFFFFFFFFFFFFF", "Test1"),
        MessageModel(text = "TestFFFFFFFFFFFFFFFFFFFFFFF", "Freeze2222"),
        MessageModel(text = "Test1", "Test1")
    )

}

fun getChatsListData(): List<ChatModel> {
    return listOf(
        ChatModel(
            "Freeze2222",
            "...",
            "#123458",
            "test1", "23:51",
            null,
            3,
            "23:52"
        ),
        ChatModel(
            "NotFreeze2222",
            "...",
            "#123457",
            "test2",
            "23:00",
            null,
            0,
            "23:00"
        ),
        ChatModel(
            "AbsolutelyNotFree",
            "test-1",
            "#123456",
            "test3",
            "0:00",
            null,
            125,
            "0:00"
        )

    )
}

fun getContactListData(): List<UserModel> {
    return listOf(
        UserModel("User1", "Test1", "#0000", null, "00:12")
    )
}

fun login(
    navController: NavController,
    username: String,
    password: String,
) {
    if (username.isNotEmpty() && password.isNotEmpty()) {
        if (isStringValid(username, USERNAME) && isStringValid(password, PASSWORD)) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password).addOnSuccessListener {
                Log.e("Login", "SuccessLogin")
            }.addOnFailureListener{
                Log.e("Login", "LoginFailed")
            }
        }
    }

    //navController.navigate(Screen.Main.route) {
        //navController.popBackStack()
    //}
}

fun createAccount(
    navController: NavController,
    username: String,
    email: String,
    password: String,
    passwordConfirmation: String
) {
    navController.navigate(Screen.Main.route) {
        navController.popBackStack()
    }
}

fun isUserOnline(chatModel: ChatModel): Boolean {
    val time = chatModel.lastSeen.split(":")
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, time[0].toInt(), time[1].toInt())
    return calendar.timeInMillis - Calendar.getInstance().timeInMillis < 0
}

fun isEmailValid(string: String): Boolean {
    return string.isNotEmpty() && string.contains('@') && isStringValid(string, EMAIL)
}

fun isUsernameValid(string: String): Boolean {
    return string.isNotEmpty() && string.length <= 16 && isStringValid(string, USERNAME)
}

fun isPasswordValid(string: String, stringConfirmation: String): Boolean {
    return if (string.isNotEmpty() && stringConfirmation.isNotEmpty()) {
        isStringValid(string, PASSWORD) && isStringValid(stringConfirmation, PASSWORD)
    } else false
}

fun isStringValid(string: String, type: String): Boolean {
    val emailRegex = """(@|.)""".toRegex()
    val rawBaseRegex = """(!|#|\$|%|^|&|\*|\)|\(|"|'|;|:|,|.|}|\{|]|\[|\?|/|\\|>|<|\+|_|-|=)"""
    lateinit var regex: Regex
    when (type) {
        "email" -> {
            regex = rawBaseRegex.replace(emailRegex, "").toRegex()
        }
        "username" -> {
            regex = rawBaseRegex.toRegex()
        }
        "password" -> {
            return string.length >= 6
        }
    }
    return !string.contains(regex)
}