package com.example.dinamicfeature.di.modules.viewmodel

import com.example.dinamicfeature.presentation.main.AppViewModel
import com.example.dinamicfeature.presentation.login.presentation.LoginViewModel
import com.example.dinamicfeature.presentation.login.presentation.createAccount.CreateAccountViewModel
import com.example.dinamicfeature.presentation.login.presentation.logout.LogoutViewModel
import com.example.dinamicfeature.presentation.login.presentation.splash.SplashViewModel
import com.example.dinamicfeature.presentation.mysignature.MySignatureViewModel
import com.example.dinamicfeature.presentation.profile.presentation.ProfileViewModel
import com.example.dinamicfeature.presentation.register.presentation.RegisterViewModel
import com.example.dinamicfeature.presentation.searchpeople.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appViewModelModule = module {

  viewModel { AppViewModel(get()) }
  viewModel { LoginViewModel(get(), get()) }
  viewModel { MySignatureViewModel(get()) }
  viewModel { ProfileViewModel(get(), get()) }
  viewModel { SearchViewModel(get(), get(), get(),get()) }
  viewModel { CreateAccountViewModel(get(), get(), get()) }
  viewModel { SplashViewModel(get()) }
  viewModel { LogoutViewModel(get()) }
  viewModel { RegisterViewModel(get(), get()) }

}