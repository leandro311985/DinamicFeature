package com.example.dinamicfeature.di.di

import com.example.dinamicfeature.di.modules.ModuleInitialization
import com.example.dinamicfeature.di.modules.db.databaseModule
import com.example.dinamicfeature.di.modules.firebase.firebaseModule
import com.example.dinamicfeature.di.modules.repositories.mainRepositoryModule
import com.example.dinamicfeature.di.modules.repositories.moshiModule
import com.example.dinamicfeature.di.modules.repositories.userRepositoryModule
import com.example.dinamicfeature.di.modules.usecases.mainUseCaseModule
import com.example.dinamicfeature.di.modules.usecases.userUseCaseModule
import com.example.dinamicfeature.di.modules.viewmodel.appViewModelModule
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
    listModules.addAll(returnMoshiModules())

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
    mainRepositoryModule
  )
}

private fun returnUseCasesModules(): List<Module> {
  return listOf(
    userUseCaseModule,
    mainUseCaseModule
  )

}

private fun returnFirebaseModules(): List<Module> {
  return listOf(
    firebaseModule
  )
}

private fun returnDbModules(): List<Module> {
  return listOf(
    databaseModule
  )
}

private fun returnMoshiModules(): List<Module> {
  return listOf(
    moshiModule
  )
}




