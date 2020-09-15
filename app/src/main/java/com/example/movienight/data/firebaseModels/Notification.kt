package com.example.movienight.data.firebaseModels

data class Notification(val title:String?,val body:String?) {
    constructor():this(null,null)
}