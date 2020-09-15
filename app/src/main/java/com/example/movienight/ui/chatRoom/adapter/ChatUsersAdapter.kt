package com.example.movienight.ui.chatRoom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.UserItemBinding
import com.example.movienight.data.firebaseModels.User

class ChatUsersAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var usersList: ArrayList<User> = ArrayList()

    inner class UserViewHolder(var binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            clickListener.onItemClick(position)
        }

        fun bindData(position: Int){
            binding.userItem=usersList[position]
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.bindData(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}