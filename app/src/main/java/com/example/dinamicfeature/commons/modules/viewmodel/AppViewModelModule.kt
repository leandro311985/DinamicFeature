package com.example.dinamicfeature.commons.modules.viewmodel

import com.example.dinamicfeature.AppViewModel
import com.example.dinamicfeature.login.presentation.LoginViewModel
import com.example.dinamicfeature.login.presentation.createAccount.CreateAccountViewModel
import com.example.dinamicfeature.login.presentation.splash.SplashViewModel
import com.example.dinamicfeature.mysignature.MySignatureViewModel
import com.example.dinamicfeature.searchpeople.presentation.SearchViewModel
import com.example.dinamicfeature.profile.presentation.ProfileViewModel
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