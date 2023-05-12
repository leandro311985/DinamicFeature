package com.example.dinamicfeature.commons.di

import com.example.dinamicfeature.commons.modules.ModuleInitialization
import com.example.dinamicfeature.commons.modules.viewmodel.appViewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.core.module.Module

@FlowPreview
@ExperimentalCoroutinesApi
class DInitialization : ModuleInitialization() {
  override fun init(): List<Module> {
    val listModules = mutableListOf<Module>()

    listModules.addAll(returnDiModules())

    return listModules
  }
}

private fun returnDiModules(): List<Module> {
  return listOf(
    appViewModelModule
  )
}



