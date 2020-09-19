package com.example.movienight.ui.chatRoom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.movienight.R
import com.example.movienight.ui.base.BaseActivity
import com.example.movienight.ui.chatRoom.adapter.ChatUsersAdapter
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.coroutines.tasks.await
import org.koin.android.viewmodel.ext.android.viewModel


class ChatActivity : BaseActivity<ChatViewModel>(){

    private val chatViewModel: ChatViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun layoutID(): Int = R.layout.activity_chat_users

    override fun getViewModel(): ChatViewModel = chatViewModel
    override fun onBackPressed() {
        finish()
    }

}