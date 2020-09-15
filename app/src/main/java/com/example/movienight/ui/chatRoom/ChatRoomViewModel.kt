package com.example.movienight.ui.chatRoom

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movienight.data.api.NotificationApiEndPoints
import com.example.movienight.data.firebaseModels.ChatMessageData
import com.example.movienight.data.firebaseModels.Notification
import com.example.movienight.data.firebaseModels.NotificationBody
import com.example.movienight.data.repository.ChatRoomRepo
import com.example.movienight.ui.base.BaseViewModel
import com.example.movienight.data.firebaseModels.UserInRoom
import com.example.movienight.network.RetrofitClient
import com.example.movienight.utilities.Constants
import com.google.firebase.database.*
import com.google.firebase.iid.FirebaseInstanceId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatRoomViewModel(chatRoomRepo: ChatRoomRepo) : BaseViewModel<ChatRoomRepo>(chatRoomRepo) {

    lateinit var id: String
    lateinit var userName: String
    lateinit var userID: String
    lateinit var notificationBody: String
    var message = MutableLiveData<String>()
    var chatData = MutableLiveData<ChatMessageData>()
    var isOnline = MutableLiveData<Boolean>()
    var fcmToken = MutableLiveData<String>()
    private var messageListener: ChildEventListener? = null
    private lateinit var chatMessageData: ChatMessageData
    private val firebaseDataBase = FirebaseDatabase.getInstance()
    private var databaseRef = firebaseDataBase.reference.child("rooms")
    fun initDatabaseRef() {
        if (checkSmaller(userID, id) == userID) {
            databaseRef = databaseRef.child(userID + "_" + id)
        } else {
            databaseRef = databaseRef.child(id + "_" + userID)
        }
    }

    fun setMyState(state: Boolean) {
        val userInRoom = UserInRoom(id, state)
        databaseRef.child(id).setValue(userInRoom)
    }


    fun sendMessage() {
        notificationBody = message.value.toString()
        chatMessageData =
            ChatMessageData(
                message.value,
                userName,
                id
            )
        databaseRef.child("messages").push().setValue(chatMessageData)
    }

    fun sendNotification() {
        val notification = Notification("new message from $userName", notificationBody)
        val notificationBody = NotificationBody(fcmToken.value, "high", notification)
        val request = RetrofitClient.getAPI(NotificationApiEndPoints::class.java)
            .sendNotification(Constants.FIREBASE_URL, notificationBody)
        request.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("sending notification", "...........")
            }

        })
    }


    fun getToken() {
        val userTokenListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                fcmToken.value = snapshot.value as String
            }
        }
        firebaseDataBase.reference.child("users").child(userID).child("fcmToken").addValueEventListener(userTokenListener)

    }

    fun checkIsOnline() {
        val isOnlineListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                isOnline.value = snapshot.value as Boolean
            }
        }
        databaseRef.child(userID).child("online").addValueEventListener(isOnlineListener)
    }

    private fun checkSmaller(id1: String, id2: String): String {
        if (id1.length < id2.length) {
            return id1
        } else if (id2.length < id1.length) {
            return id2
        } else {
            var id1_number = 0
            var id2_number = 0
            for (i in id1.indices) {
                id1_number += id1[i].toInt()
                id2_number += id2[i].toInt()
            }
            if (id1_number < id2_number) {
                return id1
            } else {
                return id2
            }
        }
    }

    fun readMessages() {
        if (messageListener == null) {
            messageListener = object : ChildEventListener {
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
            databaseRef.child("messages")
                .addChildEventListener(messageListener as ChildEventListener)
        }
    }

}