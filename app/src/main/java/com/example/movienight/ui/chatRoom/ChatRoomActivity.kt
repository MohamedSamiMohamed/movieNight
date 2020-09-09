package com.example.movienight.ui.chatRoom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movienight.R
import com.example.movienight.databinding.ActivityChatRoomBinding
import com.example.movienight.databinding.ChatRoomFragmentBinding
import com.example.movienight.ui.base.BaseActivity
import com.example.movienight.ui.chatRoom.adapter.ChatRoomAdapter
import com.example.movienight.ui.main.MainActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.viewmodel.ext.android.viewModel

class ChatRoomActivity : BaseActivity<ChatRoomViewModel>() {
    private lateinit var mFirebaseAuth: FirebaseAuth
    private val chatRoomViewModel: ChatRoomViewModel by viewModel()
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )
    private lateinit var chatRoomAdapter: ChatRoomAdapter
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private var isSignOut = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authListener = object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                val user = p0.currentUser
                if (user == null) {
                    if (isSignOut) {
                        finish()
                    } else {
                        startActivityForResult(
                            AuthUI.getInstance().createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(providers)
                                .build(), RC_SIGN_IN
                        )
                    }
                } else {
                    onSignIn(user.displayName.toString())
                }
            }
        }
        mFirebaseAuth = FirebaseAuth.getInstance()
        chatRoomAdapter = ChatRoomAdapter()
        val binding: ActivityChatRoomBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_chat_room)
        binding.chatRoomViewModel = mViewModel
        binding.lifecycleOwner = this
        binding.sendButton.setOnClickListener {
            mViewModel.sendMessage()
            binding.messageText.setText("")
        }
        binding.signOut.setOnClickListener {
            signOut()
        }
        binding.adapter = chatRoomAdapter
        mViewModel.chatData.observe(this, Observer {
            chatRoomAdapter.chatMessages.add(it)
            chatRoomAdapter.notifyDataSetChanged()
            binding.chatMessageRv.smoothScrollToPosition(chatRoomAdapter.chatMessages.count() - 1)
        })

    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this)
        isSignOut=true
        finish()
    }

    private fun onSignIn(userName: String) {
        mViewModel.userName = userName
        mViewModel.readMessages()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                onSignIn(user!!.displayName.toString())
            } else {
                if (response == null) {
                    Toast.makeText(this, "Login has been cancelled", Toast.LENGTH_SHORT)
                        .show()
                } else if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT)
                        .show()
                }
                finish()
                return

            }
        }
    }

    override fun layoutID(): Int = R.layout.activity_chat_room

    override fun getViewModel(): ChatRoomViewModel = chatRoomViewModel

    companion object {
        private const val RC_SIGN_IN = 1
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(authListener)

    }
}