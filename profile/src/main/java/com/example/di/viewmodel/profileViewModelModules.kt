package com.example.di.viewmodel

import com.example.profile.ProfileViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val profileViewModelModule = module {
  viewModel { ProfileViewModel(get(),get()) }

}