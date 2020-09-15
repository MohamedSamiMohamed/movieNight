package com.example.movienight.ui.chatRoom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.movienight.R
import com.example.movienight.databinding.ActivityChatUsersBinding
import com.example.movienight.ui.base.BaseActivity
import com.example.movienight.ui.chatRoom.adapter.ChatUsersAdapter
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.iid.FirebaseInstanceId
import org.koin.android.viewmodel.ext.android.viewModel


class ChatUsersActivity : BaseActivity<ChatUsersViewModel>(), ChatUsersAdapter.OnItemClickListener {
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var chatUsersAdapter: ChatUsersAdapter
    private val chatUsersViewModel: ChatUsersViewModel by viewModel()
    private var isSignOut = false
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private lateinit var myID: String
    private lateinit var myName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatUsersAdapter = ChatUsersAdapter(this)
        val binding: ActivityChatUsersBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_chat_users)
        binding.signOut.setOnClickListener {
            signOut()
        }
        binding.adapter = chatUsersAdapter
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
                    signIn(user)
                }
            }
        }
        mFirebaseAuth = FirebaseAuth.getInstance()

        mViewModel.chatUsers.observe(this, Observer {
            if (it.uid != myID) {
                chatUsersAdapter.usersList.add(it)
                chatUsersAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                signIn(user!!)
                FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
                    if (it.isSuccessful) {
                        mViewModel.writeNewUser(
                            user.uid,
                            user.displayName.toString(),
                            it.result!!.token
                        )
                    }
                }
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

    private fun signIn(user: FirebaseUser) {
        myID = user.uid
        myName = user.displayName.toString()
        mViewModel.getUsers()
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this)
        isSignOut = true
        finish()
    }

    override fun layoutID(): Int = R.layout.activity_chat_users

    override fun getViewModel(): ChatUsersViewModel = chatUsersViewModel
    override fun onBackPressed() {
        finish()
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(authListener)

    }

    companion object {
        private const val RC_SIGN_IN = 1
    }

    override fun onItemClick(position: Int) {
        val chatRoomFragment = ChatRoomFragment(
            myID
            , chatUsersAdapter.usersList[position].uid!!, myName
        )
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, chatRoomFragment).commit()
    }
}