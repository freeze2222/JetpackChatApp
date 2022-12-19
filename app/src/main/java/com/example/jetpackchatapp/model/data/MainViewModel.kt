package com.example.jetpackchatapp.model.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.repository.setListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    var value:Any? = null
    var messageList = ArrayList<MessageModel>()
    lateinit var currentUser : FirebaseUser
    lateinit var chatModel : ChatModel

    fun init(chatModel: ChatModel) {
        this.chatModel = chatModel
        viewModelScope.launch {
            setListener(chatModel, )
        }

    }
}

fun ReadText(): SnapshotStateList<String> {
    val _messages = mutableStateListOf<MessageModel>()
    try {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference.child("messages")

        myRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val value = snapshot.getValue(HashMap::class.java)
                if (value != null){
                    _messages.add(value.values.)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
    return _messages
}