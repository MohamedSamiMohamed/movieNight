package com.example.movienight.ui.chatRoom.di

import com.example.movienight.ui.chatRoom.ChatRoomViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ChatRoomViewModelModule: Module = module {
    viewModel { ChatRoomViewModel(get()) }
}