package com.example.jetpackchatapp.repository

import android.content.Context
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.model.UserModel
import com.example.jetpackchatapp.model.data.*
import com.example.jetpackchatapp.model.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import java.util.*

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
        FirebaseDatabase.getInstance().reference.child("users").child(userToAdd).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    Log.e("DEBUG", "D")
                    val ref = FirebaseDatabase.getInstance().reference.child("users")
                    ref.child(currentUserEmail).child("chats").child("$currentUserEmail-$userToAdd")
                        .get().addOnSuccessListener {
                            Log.e("DEBUG", "E")
                            ref.child(currentUserEmail).child("chats")
                                .child("$userToAdd-$currentUserEmail").get()
                                .addOnSuccessListener { it1 ->
                                    lateinit var currentChatModel: ChatModel
                                    lateinit var addingChatModel: ChatModel

                                    if (!it1.exists()) {
                                        Log.e("DEBUG", "SD")
                                        getChatModel(currentUserEmail, object : Callback {
                                            override fun call(T: Any?) {
                                                Log.e("DEBUG", "SDDG")
                                                currentChatModel = T as ChatModel
                                                getChatModel(userToAdd, object : Callback {
                                                    override fun call(T: Any?) {
                                                        Log.e("DEBUG", "SDDGR")
                                                        addingChatModel = T as ChatModel
                                                        ref.child(currentUserEmail).child("chats")
                                                            .child("$currentUserEmail-$userToAdd")
                                                            .setValue(addingChatModel)
                                                            .addOnSuccessListener {
                                                                ref.child(userToAdd).child("chats")
                                                                    .child("$userToAdd-$currentUserEmail")
                                                                    .setValue(currentChatModel)
                                                                    .addOnSuccessListener {
                                                                        callback.call(
                                                                            addingChatModel
                                                                        )
                                                                    }
                                                            }


                                                    }
                                                }, currentChatModel.chatUID, currentUserEmail)
                                            }
                                        }, 0, userToAdd)
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
    if (messageList.size == 1) {
        messageList.clear()
        return messageList
    } else return messageList
}

fun getChatModel(email: String, callback: Callback, chatUID: Long, secondEmail: String) {
    var counter = 0
    val testList = ArrayList<Any>()
    var secondUID: Long = 0
    FirebaseDatabase.getInstance().reference.child("users").child(secondEmail)
        .child("uid").get().addOnSuccessListener {
            secondUID = it.value as Long
        }.addOnSuccessListener {
            FirebaseDatabase.getInstance().reference.child("users").child(email)
                .addChildEventListener(
                    object : ChildEventListener {

                        override fun onChildAdded(
                            snapshot: DataSnapshot,
                            previousChildName: String?
                        ) {
                            Log.e("DEBUG", "Start")
                            if (snapshot.key != "chats" && snapshot.key != "contacts") {
                                testList.add(snapshot.value!!)
                                counter++
                            } else {
                                Log.e("DEBUG", "Chats or contacts detected")
                            }

                            Log.e("DEBUG", testList.toString())
                            if (counter == 5) {
                                val chat = ChatModel(
                                    name = testList[3].toString(),
                                    lastSeen = testList[2] as Long,
                                    last_message = "",
                                    new_messages = 0,
                                    firstUID = testList[4] as Long,
                                    secondUID = secondUID,
                                    avatarRef = "null",
                                    chatUID = (if (chatUID != 0.toLong()) chatUID else UUID.randomUUID().mostSignificantBits) as Long
                                )
                                callback.call(chat)
                            }
                        }

                        override fun onChildChanged(
                            snapshot: DataSnapshot,
                            previousChildName: String?
                        ) {
                        }

                        override fun onChildRemoved(snapshot: DataSnapshot) {}
                        override fun onChildMoved(
                            snapshot: DataSnapshot,
                            previousChildName: String?
                        ) {
                        }

                        override fun onCancelled(error: DatabaseError) {}
                    }
                )
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
                    Log.e("DEBUG", "SecondUID: $testList")
                    val chat = ChatModel(
                        name = testList[3].toString(),
                        lastSeen = testList[0] as Long,
                        last_message = testList[7] as String,
                        new_messages = (testList[2] as Long).toInt(),
                        firstUID = testList[6] as Long,
                        secondUID = testList[4] as Long,
                        avatarRef = if ((testList[5] as String) == "null") "null" else testList[6].toString(),
                        chatUID = testList[1] as Long
                    )
                    result.add(chat)
                }
                Log.e("DEBUG", result.toString())
                callback.call(result)
            }
        }
}

fun getAvatar(avatarRef: String): Int {
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
                        avatarRef = "null",
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
    context: Context,
    mainViewModel: MainViewModel
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
            FirebaseDatabase
                .getInstance()
                .reference
                .child("users")
                .child(email.replace("@", "").replace(".", ""))
                .setValue(
                    UserModel(
                        description = DEFAULT_DESCRIPTION,
                        lastSeen = Calendar.getInstance().timeInMillis,
                        name = username,
                        UID = UUID.randomUUID().mostSignificantBits
                    )
                )
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    mainViewModel.currentUser = FirebaseAuth.getInstance().currentUser!!
                    navController.navigate(Screen.Main.route) {
                        navController.popBackStack()
                    }
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