package com.example.movienight.ui.chatRoom.di

import com.example.movienight.ui.chatRoom.ChatRoomViewModel
import com.example.movienight.ui.chatRoom.ChatViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ChatViewModelModule: Module = module {
    viewModel { ChatViewModel(get()) }
}