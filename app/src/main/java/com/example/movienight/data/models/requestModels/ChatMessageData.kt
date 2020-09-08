package com.example.movienight.data.models.requestModels

data class ChatMessageData(val message:String?,val username:String?) {
constructor():this(null,null)
}