package com.example.movienight.data.api

import com.example.movienight.constants.ApiConstants
import com.example.movienight.data.firebaseModels.NotificationBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NotificationApiEndPoints {
    @Headers(
        "Content-Type:application/json",
        "Authorization:key=${ApiConstants.FIREBASE_SERVER_KEY}"

    )
    @POST
    suspend fun sendNotification(@Url url:String, @Body  notificationBody: NotificationBody ): Response<ResponseBody>
}