package com.example.movienight.data.firebaseModels

data class ChatMessageData(val message:String?,val username:String?,val uid:String?) {
constructor():this(null,null,null)
}