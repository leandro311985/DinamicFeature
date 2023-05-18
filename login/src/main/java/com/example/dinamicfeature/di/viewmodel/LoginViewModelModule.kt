package com.example.dinamicfeature.di.viewmodel

import com.example.dinamicfeature.createAccount.CreateAccountViewModel
import com.example.dinamicfeature.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@ExperimentalCoroutinesApi
val loginViewModelModule = module {
  viewModel { LoginViewModel(get(),get()) }
  viewModel { CreateAccountViewModel(get()) }

}