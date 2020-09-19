package com.example.movienight.ui.chatRoom

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movienight.R
import com.example.movienight.constants.KeyConstants
import com.example.movienight.databinding.ChatUsersFragmentBinding
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.chatRoom.adapter.ChatUsersAdapter
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.iid.FirebaseInstanceId
import org.koin.android.viewmodel.ext.android.viewModel

class ChatUsersFragment : BaseFragment<ChatUsersViewModel>(), ChatUsersAdapter.OnItemClickListener {
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var chatUsersAdapter: ChatUsersAdapter
    private val chatUsersViewModel: ChatUsersViewModel by viewModel()
    private lateinit var navController: NavController
    private var isSignOut = false
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private lateinit var myID: String
    private lateinit var myName: String

    companion object {
        private const val RC_SIGN_IN = 1
        fun newInstance() =
            ChatUsersFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("chat users","..........")
        chatUsersAdapter = ChatUsersAdapter(this)
        val binding: ChatUsersFragmentBinding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.chat_users_fragment)
        binding.signOut.setOnClickListener {
            signOut()
        }
        navController = Navigation.findNavController(view)
        binding.adapter = chatUsersAdapter
        authListener = object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                val user = p0.currentUser
                if (user == null) {
                    if (isSignOut) {
                        requireActivity().finish()
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

        mViewModel.chatUsers.observe(viewLifecycleOwner, Observer {
            mViewModel.isLoading.value = false
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
            if (resultCode == AppCompatActivity.RESULT_OK) {
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
                    Toast.makeText(requireContext(), "Login has been cancelled", Toast.LENGTH_SHORT)
                        .show()
                } else if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT)
                        .show()
                }
                requireActivity().finish()
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
        AuthUI.getInstance().signOut(requireContext())
        isSignOut = true
        requireActivity().finish()
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(authListener)

    }

    override fun getViewModel(): ChatUsersViewModel = chatUsersViewModel

    override fun layoutID(): Int = R.layout.chat_users_fragment

    override fun onItemClick(position: Int) {
        val args = bundleOf(
            KeyConstants.USER_ID to chatUsersAdapter.usersList[position].uid,
            KeyConstants.MY_NAME to myName,
            KeyConstants.MY_ID to myID
        )
        navController.navigate(R.id.action_chatUsersFragment_to_chatRoomFragment, args)

    }

}