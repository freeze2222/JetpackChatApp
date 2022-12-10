package com.example.jetpackchatapp.model.data

import android.media.MediaDrm
import com.example.jetpackchatapp.R
import com.google.firebase.database.FirebaseDatabase

const val USERNAME = "username"
const val EMAIL = "email"
const val PASSWORD = "password"
const val DEFAULT_DESCRIPTION = "I'm just registered in Xenogram!"
const val ERROR_EMPTY = "Fill all fields"
const val ERROR_USERNAME = "Username is not valid"
const val ERROR_PASSWORD = "Password must start with number or letter and be in 4..16 length"
const val ERROR_EMAIL = "Email is not valid"
const val EMPTY_REFERENCE = "null"
const val DATE_FORMAT = "dd/MM hh:mm"

val titleData = listOf(
    "Sign In To Your Account",
    "Create a Account",
    "Chats",
    "Contacts",
    "Settings",
    "Account Settings" //5
)
val descriptionData = listOf(
    "Username",
    "Password",
    "* * * * * *",
    "Sing In",
    "Sing Up",
    "Email", //5
    "freeze2222@gmail.com",
    "freeze2222",
    "Password Again",
    "Type a message",
    "Online", //10
    "Account",
    "Log Out",
    "Update Profile"
)
val imageData = listOf(
    R.drawable.splash_logo,
    R.drawable.logo,
    R.drawable.line,
    R.drawable.icon_user,
    R.drawable.icon_lock,
    R.drawable.icon_mail, //5
    R.drawable.small_logo,
    R.drawable.icon_arrow,
    R.drawable.paper_plane,
    R.drawable.no_avatar,
    R.drawable.icon_message, //10
    R.drawable.user_icon_yellow,
    R.drawable.icon_power
)

