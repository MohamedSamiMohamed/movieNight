package com.example.movienight.ui.chatRoom

import androidx.lifecycle.MutableLiveData
import com.example.movienight.constants.FirebaseRoutes
import com.example.movienight.data.repository.ChatUsersRepo
import com.example.movienight.ui.base.BaseViewModel
import com.example.movienight.data.firebaseModels.User
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class ChatUsersViewModel(chatUsersRepo: ChatUsersRepo) :
    BaseViewModel<ChatUsersRepo>(chatUsersRepo) {
    private val firebaseDataBase = FirebaseDatabase.getInstance()
    private val usersDatabase = firebaseDataBase.reference.child(FirebaseRoutes.USERS)
    private var userDataListener: ChildEventListener? = null
    val chatUsers=MutableLiveData<User>()

    fun writeNewUser(uID: String, name: String, token: String) {
        val user = User(uID, name,token)
        usersDatabase.child(uID).setValue(user)
    }

    fun getUsers() {
        isLoading.value=true
        if ( userDataListener== null) {
            userDataListener = object : ChildEventListener{
                override fun onCancelled(error: DatabaseError) {}
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    chatUsers.value = snapshot.getValue(User::class.java)
                }
                override fun onChildRemoved(snapshot: DataSnapshot) {}
            }
            usersDatabase.addChildEventListener(userDataListener as ChildEventListener)
        }
    }

}