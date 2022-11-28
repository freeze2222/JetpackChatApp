package com.example.jetpackchatapp.repository

import android.content.Context
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.navigation.NavController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.model.navigation.Screen
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


fun getMessagesListData(chatModel: ChatModel): List<MessageModel> {
    val reference = FirebaseDatabase.getInstance().reference.child("chats").child(chatModel.ChatUID)
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
            "#123456",
            "test1", "23:51",
            null,
            3,
            "23:52"
        ),
        ChatModel(
            "NotFreeze2222",
            "...",
            "#123457",
            "#123456",
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
            "#123456",
            "test3",
            "0:00",
            null,
            125,
            "0:00"
        )

    )
}

fun getContactListData(username: String): List<UserModel> {
    val reference = FirebaseDatabase.getInstance().reference.child("users").child("contacts").get()
        .addOnSuccessListener(
            OnSuccessListener {
                if (it.exists()){
                    it.getValue()
                }
            })
    return listOf()
}

fun login(
    navController: NavController,
    email: String,
    password: String,
    context: Context
) {
    if (email.isEmpty() || password.isEmpty()) {
        Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
        return
    }
    if (!isStringValid(email, EMAIL)) {
        Toast.makeText(context, "Username is not valid", Toast.LENGTH_SHORT).show()
        return
    }
    if (!isStringValid(password, PASSWORD)) {
        Toast.makeText(context, "Password is not valid", Toast.LENGTH_SHORT).show()
        return
    }
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnSuccessListener {
            navController.navigate(Screen.Main.route) {
                navController.popBackStack()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Incorrect credentials", Toast.LENGTH_SHORT).show()
        }
}

fun createAccount(
    navController: NavController,
    username: String,
    email: String,
    password: String,
    passwordConfirmation: String,
    context: Context
) {
    if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
        Toast.makeText(context, ERROR_EMPTY, Toast.LENGTH_SHORT).show()
        return
    }
    if (!isUsernameValid(username)) {
        Toast.makeText(context, ERROR_USERNAME, Toast.LENGTH_SHORT).show()
        return
    }
    if (!isPasswordValid(password, passwordConfirmation)) {
        Toast.makeText(context, ERROR_PASSWORD, Toast.LENGTH_SHORT).show()
        return
    }
    if (!isEmailValid(email)) {
        Toast.makeText(context, ERROR_EMAIL, Toast.LENGTH_SHORT).show()
        return
    }
    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).addOnCompleteListener(
        OnCompleteListener {
            val isNewUser = it.result.signInMethods!!.isEmpty()
            if (isNewUser) {
                val reference =
                    FirebaseDatabase
                        .getInstance()
                        .reference
                        .child("users")
                        .child(email.replace("@", "").replace(".", ""))
                reference.setValue(
                    UserModel(
                        username, DEFAULT_DESCRIPTION, UUID.randomUUID(),
                        avatar = null, Calendar.getInstance().timeInMillis
                    )
                )
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                navController.navigate(Screen.Main.route) {
                    navController.popBackStack()
                }
            } else {
                Toast.makeText(context, "User is already registered", Toast.LENGTH_SHORT).show()
            }
        })
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
        if (isStringValid(string, PASSWORD) && isStringValid(stringConfirmation, PASSWORD)) {
            string == stringConfirmation
        } else {
            false
        }
    } else {
        false
    }
}

fun isStringValid(string: String, type: String): Boolean {
    val usernameRegex = """^[A-Za-z][A-Za-z0-9_]{3,16}$""".toRegex()
    val passwordRegex = """^[0-9A-Za-z][A-Za-z0-9_!-&$*-/:-=]{3,16}$""".toRegex()
    return when (type) {
        "email" -> {
            EMAIL_ADDRESS.matcher(string).matches()
        }
        "username" -> {
            usernameRegex.matches(string)
        }
        "password" -> {
            passwordRegex.matches(string)
        }
        else -> false
    }
}