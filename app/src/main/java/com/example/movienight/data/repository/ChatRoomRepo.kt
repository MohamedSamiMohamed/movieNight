package com.example.movienight.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movienight.constants.ApiConstants
import com.example.movienight.data.api.NotificationApiEndPoints
import com.example.movienight.data.base.BaseRepo
import com.example.movienight.data.firebaseModels.NotificationBody
import com.example.movienight.network.RetrofitClient
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.Exception

class ChatRoomRepo : BaseRepo() {
    suspend fun sendNotification(notificationBody: NotificationBody): Response<ResponseBody> {
        return GlobalScope.async(Dispatchers.IO) {
            RetrofitClient.getAPI(NotificationApiEndPoints::class.java)
                .sendNotification(ApiConstants.FIREBASE_URL, notificationBody)
        }.await()
    }
}