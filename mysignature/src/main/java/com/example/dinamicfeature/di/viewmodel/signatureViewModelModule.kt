package com.example.dinamicfeature.di.viewmodel

import com.example.dinamicfeature.mysignature.MySignatureViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val signatureViewModelModule = module {
  viewModel { MySignatureViewModel(get()) }


}