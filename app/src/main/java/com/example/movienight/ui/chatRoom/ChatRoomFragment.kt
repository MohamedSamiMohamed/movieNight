package com.example.movienight.ui.chatRoom

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.movienight.R
import com.example.movienight.databinding.ChatRoomFragmentBinding
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.chatRoom.adapter.ChatRoomAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ChatRoomFragment(val myID: String, val userID: String, val myName: String) :
    BaseFragment<ChatRoomViewModel>() {
    private val chatRoomViewModel: ChatRoomViewModel by viewModel()
    private lateinit var chatRoomAdapter: ChatRoomAdapter

    companion object {
        fun newInstance() = ChatRoomFragment("", "", "")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatRoomAdapter = ChatRoomAdapter()

        val binding: ChatRoomFragmentBinding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.chat_room_fragment)
        binding.chatRoomViewModel = mViewModel
        binding.lifecycleOwner = this
        mViewModel.userID = userID
        mViewModel.id = myID
        mViewModel.userName = myName
        mViewModel.initDatabaseRef()
        mViewModel.setMyState(true)
        binding.sendButton.setOnClickListener {
            mViewModel.sendMessage()
            mViewModel.checkIsOnline()
            binding.messageText.setText("")
        }
        binding.adapter = chatRoomAdapter
        mViewModel.readMessages()
        mViewModel.chatData.observe(viewLifecycleOwner, Observer {
            chatRoomAdapter.chatMessages.add(it)
            chatRoomAdapter.notifyDataSetChanged()
            binding.chatMessageRv.smoothScrollToPosition(chatRoomAdapter.chatMessages.count() - 1)
        })

        mViewModel.isOnline.observe(viewLifecycleOwner, Observer {
            if (!it) {
                mViewModel.getToken()
            }
        })

        mViewModel.fcmToken.observe(viewLifecycleOwner, Observer {
            mViewModel.sendNotification()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.setMyState(false)
    }

    override fun getViewModel(): ChatRoomViewModel = chatRoomViewModel
    override fun layoutID(): Int = R.layout.chat_room_fragment

}