package com.example.jetpackchatapp.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import kotlin.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.model.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

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

fun addChat(callback: Callback, currentUserEmailRaw: String, userToAddRaw: String) {
    val currentUserEmail = currentUserEmailRaw.replace("@", "")
                                              .replace(".", "")
    val userToAdd = userToAddRaw.replace("@", "")
                                .replace(".", "")
    if (isEmailValid(currentUserEmailRaw) && isEmailValid(userToAddRaw)) {
        Log.e("DEBUG","D")
        val ref = FirebaseDatabase.getInstance().reference.child("users")
            .child(
                currentUserEmail
                    .replace("@", "")
                    .replace(".", "")
            )
            .child("chats")
        ref.child("$currentUserEmail-$userToAdd").get().addOnSuccessListener { it ->
            if (it.exists()) {
                Log.e("DEBUG","D")
                getChatModel(userToAdd, object : Callback {
                    override fun call(T: Any?) {
                        Log.e("DEBUG","F")
                        callback.call(T)
                    }
                })
            } else {
                Log.e("DEBUG","E")
                ref.child("$userToAdd-$currentUserEmail").get().addOnSuccessListener { it1 ->
                    if (it1.exists()) {
                        Log.e("DEBUG","ED")
                        getChatModel(userToAdd, object : Callback {
                            override fun call(T: Any?) {
                                Log.e("DEBUG","EDD")
                                callback.call(T)
                            }
                        })
                    } else {
                        Log.e("DEBUG","SD")
                        ref.child(userToAdd).get().addOnSuccessListener {
                            if (it.exists()) {
                                Log.e("DEBUG","SDD")
                                lateinit var currentChatModel: ChatModel
                                lateinit var addingChatModel: ChatModel
                                getChatModel(currentUserEmail, object : Callback {
                                    override fun call(T: Any?) {
                                        Log.e("DEBUG","SDDG")
                                        currentChatModel = T as ChatModel
                                        getChatModel(userToAdd, object : Callback {
                                            override fun call(T: Any?) {
                                                addingChatModel = T as ChatModel
                                                ref.child(currentUserEmail).child("chats")
                                                    .child("$currentUserEmail-$userToAdd")
                                                    .setValue(addingChatModel)
                                                ref.child(userToAdd).child("chats")
                                                    .child("$userToAdd-$currentUserEmail")
                                                    .setValue(currentChatModel)
                                                callback.call(
                                                    getChatModel(userToAdd, object : Callback {
                                                        override fun call(T: Any?) {
                                                            Log.e("DEBUG","SDDGE")
                                                            callback.call(T)
                                                        }
                                                    })
                                                )
                                            }
                                        })
                                    }
                                })

                            }
                        }
                    }
                }
            }
        }
    }
}

fun setListener(
    chatModel: ChatModel,
    lazyListState: MutableState<LazyListState>?,
    coroutineScope: CoroutineScope,
    size: Int
): SnapshotStateList<MessageModel> {
    var messageList = mutableStateListOf<MessageModel>()
    val ref = FirebaseDatabase.getInstance().reference.child("messages")
        .child(chatModel.chatUID.toString())
    val messageListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            val message: MessageModel = dataSnapshot.getValue(MessageModel::class.java)!!
            messageList.add(message)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(databaseError: DatabaseError) {}
    }
    ref.addChildEventListener(messageListener)
    return messageList
}

fun getChatModel(email: String, callback: Callback) {
    FirebaseDatabase.getInstance().reference.child("users").child(email).addChildEventListener(
        object : ChildEventListener {

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val testList = ArrayList<Any>()
                val result = ArrayList<ChatModel>()
                (snapshot.value as HashMap<*, *>).toList().forEach { it1 ->
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
                callback.call(result)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        }
    )
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