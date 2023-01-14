package com.example.jetpackchatapp.model.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackchatapp.model.ChatModel
import com.example.jetpackchatapp.model.MessageModel
import com.example.jetpackchatapp.repository.addChat
import com.example.jetpackchatapp.repository.setListener
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var value: Any? = null
    var lazyListState: MutableState<LazyListState>? = null
    val messageList: MutableState<List<MessageModel>> =
        mutableStateOf(listOf(MessageModel()))
    lateinit var currentUser: FirebaseUser
    lateinit var chatModel: ChatModel
    lateinit var testMutableList: MutableList<MessageModel>
    lateinit var coroutineScope: CoroutineScope
    lateinit var name: String

    fun setActiveChat(chatModel1: ChatModel, callback: Callback) {
        viewModelScope.launch {
            chatModel = chatModel1
            messageList.value =
                setListener(chatModel, lazyListState, coroutineScope, messageList.value.size)
            callback.call(null)
        }
    }

    internal fun addChat(emailToAdd: String, callback: Callback) {
        addChat(object : Callback {
            override fun call(T: Any?) {
                callback.call(T)
            }

        }, currentUserEmailRaw = currentUser.email!!, userToAddRaw = emailToAdd)
    }
}
