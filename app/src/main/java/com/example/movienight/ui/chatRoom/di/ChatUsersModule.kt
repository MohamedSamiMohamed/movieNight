package com.example.movienight.ui.chatRoom.di

import com.example.movienight.ui.chatRoom.ChatRoomViewModel
import com.example.movienight.ui.chatRoom.ChatUsersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ChatUsersViewModelModule : Module = module {
    viewModel { ChatUsersViewModel(get()) }
}