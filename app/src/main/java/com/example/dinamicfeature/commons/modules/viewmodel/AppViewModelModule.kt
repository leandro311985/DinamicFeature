package com.example.dinamicfeature.commons.modules.viewmodel

import com.example.dinamicfeature.AppViewModel
import com.example.dinamicfeature.login.LoginViewModel
import com.example.dinamicfeature.login.createAccount.CreateAccountViewModel
import com.example.dinamicfeature.login.splash.SplashViewModel
import com.example.dinamicfeature.mysignature.MySignatureViewModel
import com.example.dinamicfeature.searchpeople.SearchViewModel
import com.example.profile.ProfileViewModel
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