package com.example.movienight.data.firebaseModels

data class UserInRoom(val uid:String?,val online:Boolean?,val fcmToken:String?) {
    constructor():this(null,null,null)
}