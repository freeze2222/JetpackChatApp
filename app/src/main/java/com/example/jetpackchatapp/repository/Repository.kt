package com.example.jetpackchatapp.repository

import android.content.Context
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.navigation.NavController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.model.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

fun sendMessage(messageModel: MessageModel, uid: Long) {
    FirebaseDatabase.getInstance()
        .reference
        .child("messages")
        .child(uid.toString()).push()
        .setValue(messageModel)
}

fun getUsername(email: String, callback: Callback) {
    FirebaseDatabase.getInstance().reference
        .child("users")
        .child(email.replace("@", "").replace(".", ""))
        .child("name")
        .get().addOnSuccessListener {
            callback.call(it.value)
        }
}

fun parseMessage(text: String, from_user: String): MessageModel {
    return MessageModel(text, from_user)
}

fun getMessagesListData(chatModel: ChatModel, callback: Callback) {
    val reference =
        FirebaseDatabase.getInstance().reference.child("messages") .child(chatModel.chatUID.toString())
    reference.get()
        .addOnSuccessListener {
            if (it.exists()) {
                val a = (it.value as HashMap<*, *>).toList().toMutableList()
                val list = ArrayList<Any>()
                for (i in a) {
                    Log.e("DEBUG", i.second.toString())
                    list.add((i.second) as HashMap<*, *>)
                }
                val testList = ArrayList<Any>()
                val result = ArrayList<MessageModel>()
                for (i in list) {
                    (i as HashMap<*, *>).toList().forEach { it1 ->
                        testList.add(it1.second)
                    }
                    val message = MessageModel(
                        text = testList[0] as String,
                        from_user = testList[1] as String
                    )
                    result.add(message)
                }
                callback.call(result)
            }

        }
}

fun getChatsListData(email: String, callback: Callback) {
    val reference =
        FirebaseDatabase.getInstance().reference.child("users").child(email).child("chats")
    reference.get()
        .addOnSuccessListener {
            if (it.exists()) {
                val a = (it.value as HashMap<*, *>).toList().toMutableList()
                val list = ArrayList<Any>()
                for (i in a) {
                    list.add((i.second) as HashMap<*, *>)
                }
                val testList = ArrayList<Any>()
                val result = ArrayList<ChatModel>()
                for (i in list) {
                    (i as HashMap<*, *>).toList().forEach { it1 ->
                        testList.add(it1.second)
                    }
                    val chat = ChatModel(
                        name = testList[2].toString(),
                        lastSeen = testList[0] as Long,
                        last_message = testList[5] as String,
                        new_messages = (testList[1] as Long).toInt(),
                        firstUID = testList[4] as Long,
                        secondUID = testList[3] as Long,
                        avatarRef = if ((testList[6] as String).isEmpty()) FirebaseDatabase.getInstance().reference.child(
                            EMPTY_REFERENCE
                        ) else FirebaseDatabase.getInstance().reference.child(
                            testList[6].toString()
                        ),
                        chatUID = testList[7] as Long
                    )
                    result.add(chat)
                }
                callback.call(result)
            }
        }
}

fun getAvatar(avatarRef: DatabaseReference): Int {
    return 0
}

fun getContactListData(email: String, callback: Callback) {
    val reference =
        FirebaseDatabase.getInstance().reference.child("users").child(email).child("contacts")
    reference.get()
        .addOnSuccessListener {
            if (it.exists()) {
                val a = (it.value as HashMap<*, *>).toList().toMutableList()
                val list = ArrayList<Any>()
                for (i in a) {
                    list.add((i.second) as HashMap<*, *>)
                }
                val testList = ArrayList<Any>()
                val result = ArrayList<UserModel>()
                for (i in list) {
                    (i as HashMap<*, *>).toList().forEach { it1 ->
                        testList.add(it1.second)
                    }
                    val user = UserModel(
                        name = testList[2].toString(),
                        description = testList[3].toString(),
                        UID = testList[0] as Long,
                        avatarRef = if (testList[4].toString()
                                .isEmpty()
                        ) FirebaseDatabase.getInstance().reference.child("null") else FirebaseDatabase.getInstance().reference,
                        lastSeen = testList[1] as Long
                    )
                    result.add(user)
                }
                callback.call(result)
            }
        }
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
    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).addOnCompleteListener {
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
                    description = DEFAULT_DESCRIPTION,
                    lastSeen = Calendar.getInstance().timeInMillis,
                    name = username,
                    UID = UUID.randomUUID().mostSignificantBits
                )
            )
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            navController.navigate(Screen.Main.route) {
                navController.popBackStack()
            }
        } else {
            Toast.makeText(context, "User is already registered", Toast.LENGTH_SHORT).show()
        }
    }
}

fun isUserOnline(chatModel: ChatModel): Boolean {
    val time = chatModel.lastSeen
    return time - Calendar.getInstance().timeInMillis < 0
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