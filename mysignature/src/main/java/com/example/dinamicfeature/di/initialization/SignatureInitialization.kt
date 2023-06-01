package com.example.dinamicfeature.di.initialization

import com.example.dinamicfeature.commons.modules.ModuleInitialization
import com.example.dinamicfeature.di.viewmodel.signatureViewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
class SignatureInitialization : ModuleInitialization() {
  override fun init(): List<Module> {
    return listOf(
      signatureViewModelModule
    )
  }
}