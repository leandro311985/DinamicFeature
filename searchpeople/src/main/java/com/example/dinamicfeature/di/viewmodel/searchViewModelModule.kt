package com.example.dinamicfeature.di.viewmodel

import com.example.dinamicfeature.searchpeople.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val searchViewModelModule = module {
  viewModel { SearchViewModel(get()) }

}