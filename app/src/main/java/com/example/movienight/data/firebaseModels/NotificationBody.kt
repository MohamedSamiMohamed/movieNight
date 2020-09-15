package com.example.movienight.data.firebaseModels

data class NotificationBody(
    val to:String?,
    val priority:String?="high",
    val data: Notification?
){
    constructor():this(null,null,null)
}
