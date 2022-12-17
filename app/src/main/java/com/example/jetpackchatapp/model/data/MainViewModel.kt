package com.example.jetpackchatapp.model.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.repository.setListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

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