package com.example.dinamicfeature.di.viewmodel

import com.example.dinamicfeature.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



@ExperimentalCoroutinesApi
val homeViewModelModule = module {
  viewModel { HomeViewModel(get(),get()) }

}