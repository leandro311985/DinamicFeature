package com.example.dinamicfeature.commons.di

import com.example.dinamicfeature.commons.modules.ModuleInitialization
import com.example.dinamicfeature.commons.modules.db.databaseModule
import com.example.dinamicfeature.commons.modules.firebase.networkModule
import com.example.dinamicfeature.commons.modules.repositories.userRepositoryModule
import com.example.dinamicfeature.commons.modules.usecases.userUseCaseModule
import com.example.dinamicfeature.commons.modules.viewmodel.appViewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.core.module.Module

@FlowPreview
@ExperimentalCoroutinesApi
class DInitialization : ModuleInitialization() {
  override fun init(): List<Module> {
    val listModules = mutableListOf<Module>()

    listModules.addAll(returnViewModelModules())
    listModules.addAll(returnUserRepositoryModules())
    listModules.addAll(returnUseCasesModules())
    listModules.addAll(returnFirebaseModules())
    listModules.addAll(returnDbModules())

    return listModules
  }
}

private fun returnViewModelModules(): List<Module> {
  return listOf(
    appViewModelModule
  )
}

private fun returnUserRepositoryModules(): List<Module> {
  return listOf(
    userRepositoryModule,
  )
}

private fun returnUseCasesModules(): List<Module> {
  return listOf(
    userUseCaseModule
  )

}

private fun returnFirebaseModules(): List<Module> {
  return listOf(
    networkModule
  )
}

private fun returnDbModules(): List<Module> {
  return listOf(
    databaseModule
  )
}



