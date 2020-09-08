package com.example.movienight.ui.chatRoom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.data.models.requestModels.ChatMessageData
import com.example.movienight.databinding.MessageItemBinding


class ChatRoomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var chatMessages: ArrayList<ChatMessageData> = ArrayList()

    inner class ChatRoomViewHolder(var binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.chatMessage = chatMessages[position]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        return ChatRoomViewHolder(MessageItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChatRoomViewHolder -> {
                holder.bindData(position)
            }
        }
    }

}