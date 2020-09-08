package com.example.movienight.ui.chatRoom

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movienight.data.models.requestModels.ChatMessageData
import com.example.movienight.data.repository.ChatRoomRepo
import com.example.movienight.ui.base.BaseViewModel
import com.google.firebase.database.*

class ChatRoomViewModel(chatRoomRepo: ChatRoomRepo) : BaseViewModel<ChatRoomRepo>(chatRoomRepo) {
    lateinit var userName : String
    var message = MutableLiveData<String>()
    var chatData = MutableLiveData<ChatMessageData>()
    private var messageListener: ChildEventListener? = null
    private lateinit var chatMessageData: ChatMessageData
    private val firebaseDataBase = FirebaseDatabase.getInstance()
    private val messagesDatabase = firebaseDataBase.reference.child("messages")
    fun sendMessage() {
        chatMessageData = ChatMessageData(message.value, userName)
        messagesDatabase.push().setValue(chatMessageData)
    }

    fun readMessages() {
        if (messageListener == null) {
            messageListener = object : ChildEventListener{
                override fun onCancelled(error: DatabaseError) {
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    chatData.value = snapshot.getValue(ChatMessageData::class.java)
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                }
            }
            messagesDatabase.addChildEventListener(messageListener as ChildEventListener)
        }
    }

}