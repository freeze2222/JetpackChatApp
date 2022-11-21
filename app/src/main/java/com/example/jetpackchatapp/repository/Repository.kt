package com.example.jetpackchatapp.repository

import androidx.navigation.NavController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.navigation.Screen
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
        ChatModel("Freeze2222",
            "...",
            "#123458",
            "test1", "23:51",
            null,
            3,
            "23:52"),
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
            "AbsolutelyNotFreeze2222",
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

fun login(navController: NavController) {
    //FirebaseAuth.getInstance().signInWithEmailAndPassword("","")
    navController.navigate(Screen.Main.route) {
        popUpTo(Screen.SignIn.route) {
            inclusive = true
        }
    }
}

fun createAccount(navController: NavController) {
    //TODO
    navController.navigate(Screen.Main.route) {
        popUpTo(0)
    }
}

fun isUserOnline(chatModel: ChatModel): Boolean {
    val time = chatModel.lastSeen.split(":")
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, time[0].toInt(), time[1].toInt())
    return calendar.timeInMillis - Calendar.getInstance().timeInMillis < 0
}