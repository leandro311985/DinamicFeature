package com.example.dinamicfeature.di.modules.viewmodel

import com.example.dinamicfeature.AppViewModel
import com.example.dinamicfeature.presentation.login.presentation.LoginViewModel
import com.example.dinamicfeature.presentation.login.presentation.createAccount.CreateAccountViewModel
import com.example.dinamicfeature.presentation.login.presentation.splash.SplashViewModel
import com.example.dinamicfeature.presentation.mysignature.MySignatureViewModel
import com.example.dinamicfeature.presentation.searchpeople.presentation.SearchViewModel
import com.example.dinamicfeature.presentation.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appViewModelModule = module {

  viewModel { AppViewModel(get()) }
  viewModel { LoginViewModel(get(), get()) }
  viewModel { MySignatureViewModel(get()) }
  viewModel { ProfileViewModel(get(), get()) }
  viewModel { SearchViewModel(get(), get(),get()) }
  viewModel { CreateAccountViewModel(get(), get(), get()) }
  viewModel { SplashViewModel(get()) }

}