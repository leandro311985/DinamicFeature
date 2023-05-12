package com.example.dinamicfeature.commons.modules.viewmodel

import com.example.dinamicfeature.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appViewModelModule = module {

  viewModel { AppViewModel() }

}