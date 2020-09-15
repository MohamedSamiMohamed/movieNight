package com.example.movienight.data.firebaseModels

data class UserInRoom(val uid:String?,val online:Boolean?) {
    constructor():this(null,null)
}