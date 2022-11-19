package com.example.jetpackchatapp.model.data

import com.example.jetpackchatapp.model.UserModel

class ViewModel (var userModel: UserModel){
    fun getUser(): UserModel{
        return userModel
    }
}