package com.example.jetpackchatapp.model.data

import com.example.jetpackchatapp.model.ChatModel

class ViewModel() {
    var chatModel: ChatModel? = null
    fun getUser(): ChatModel {
        return chatModel!!
    }
    fun setModel(data: ChatModel) {
        chatModel = data
    }
}