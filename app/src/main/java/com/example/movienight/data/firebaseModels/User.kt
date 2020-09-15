package com.example.movienight.data.firebaseModels

data class User(val uid: String?, val userName: String?,val fcmToken:String?) {
    constructor() : this(null, null,null)
}