package com.example.movienight.data.api

import com.example.movienight.utilities.Constants
import retrofit2.Response
import com.example.movienight.data.firebaseModels.NotificationBody
import retrofit2.Call
import retrofit2.http.*

interface NotificationApiEndPoints {
    @Headers(
        "Content-Type:application/json",
        "Authorization:key=${Constants.FIREBASE_SERVER_KEY}"

    )
    @POST
    fun sendNotification(@Url url:String, @Body  notificationBody: NotificationBody ): Call<Void>
}