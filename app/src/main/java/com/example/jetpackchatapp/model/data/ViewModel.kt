package com.example.jetpackchatapp.model.data

import com.example.jetpackchatapp.model.ChatModel

class ViewModel(var text: String = "") {
    var chatModel: ChatModel? = null
    fun getUser(): ChatModel {
        return chatModel!!
    }
    @JvmName("getText1")
    fun getText():String{
        return text
    }
    fun setModel(data: ChatModel) {
        chatModel = data
    }
}