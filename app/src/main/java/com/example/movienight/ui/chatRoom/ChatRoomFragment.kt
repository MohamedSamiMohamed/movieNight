package com.example.movienight.ui.chatRoom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movienight.R
import com.example.movienight.databinding.ChatRoomFragmentBinding
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.chatRoom.adapter.ChatRoomAdapter
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.viewmodel.ext.android.viewModel


class ChatRoomFragment : BaseFragment<ChatRoomViewModel>() {
    private lateinit var mFirebaseAuth: FirebaseAuth
    private val chatRoomViewModel: ChatRoomViewModel by viewModel()
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )
    private lateinit var chatRoomAdapter: ChatRoomAdapter
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private lateinit var navController: NavController

    companion object {
        private const val RC_SIGN_IN = 1
        fun newInstance() = ChatRoomFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authListener =object :FirebaseAuth.AuthStateListener{
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                val user=p0.currentUser
                if(isAdded) {
                    if (user == null) {
                        startActivityForResult(
                            AuthUI.getInstance().createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(providers)
                                .build(), RC_SIGN_IN
                        )
                    } else {
                        onSignIn(user.displayName.toString())
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseAuth.addAuthStateListener(authListener)
        chatRoomAdapter = ChatRoomAdapter()
        navController= Navigation.findNavController(view)
        val binding: ChatRoomFragmentBinding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.chat_room_fragment)
        binding.chatRoomViewModel = mViewModel
        binding.lifecycleOwner = this
        binding.sendButton.setOnClickListener {
            mViewModel.sendMessage()
            binding.messageText.setText("")
        }
        binding.signOut.setOnClickListener{
            signOut()
        }
        binding.adapter = chatRoomAdapter
        mViewModel.chatData.observe(viewLifecycleOwner, Observer {
            chatRoomAdapter.chatMessages.add(it)
            chatRoomAdapter.notifyDataSetChanged()
            binding.chatMessageRv.smoothScrollToPosition(chatRoomAdapter.chatMessages.count() - 1)
        })
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(requireActivity())
        navController.navigate(R.id.action_chatRoomFragment_to_popularMoviesFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                onSignIn(user!!.displayName.toString())
            }
            else{
                if(response==null){
                    Toast.makeText(requireContext(),"Login has been cancelled",Toast.LENGTH_SHORT).show()
                }
                else if(response.error!!.errorCode==ErrorCodes.NO_NETWORK){
                    Toast.makeText(requireContext(),"No internet connection",Toast.LENGTH_SHORT).show()
                }
                navController.navigate(R.id.action_chatRoomFragment_to_popularMoviesFragment)
                requireActivity().finish()
                return

            }
        }
    }

    private fun onSignIn(userName:String){
        mViewModel.userName = userName
        mViewModel.readMessages()
    }

    override fun getViewModel(): ChatRoomViewModel = chatRoomViewModel
    override fun layoutID(): Int = R.layout.chat_room_fragment

}