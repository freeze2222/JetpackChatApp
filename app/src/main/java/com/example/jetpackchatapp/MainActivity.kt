package com.example.jetpackchatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackchatapp.model.data.MainViewModel
import com.example.jetpackchatapp.ui.screens.*
import com.example.jetpackchatapp.ui.theme.JetpackChatAppTheme
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    //private lateinit var database: DatabaseReference

    val viewModel : MainViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.database.setPersistenceEnabled(true)
        //database = Firebase.database.reference
        //database.child("users").child("user04").setValue("user_changed")

        setContent {
         //   val mainViewModel = MainViewModel()
            JetpackChatAppTheme {
                FrameScreen(viewModel)
            }
        }
    }

    var users = mutableStateListOf<String>()

    @Composable
    private fun ReadDatabase(ref: DatabaseReference) {
        val myRef = ref.child("messages")

        LazyColumn() {
            items(users) { user ->
                Text(text = user)
            }
        }

        myRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                users.add(snapshot.value.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                users.add(snapshot.value.toString())
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    @Composable
    fun WriteDatabase(ref: DatabaseReference) {
        val child = ref.child("messages")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            Button(onClick = { child.push().setValue("messages") }) {
                Text(text = "Отправить")
            }
        }
    }
}

