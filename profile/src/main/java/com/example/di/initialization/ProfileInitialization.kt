package com.example.di.initialization

import com.example.di.viewmodel.profileViewModelModule
import com.example.dinamicfeature.commons.modules.ModuleInitialization
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
class ProfileInitialization : ModuleInitialization() {
  override fun init(): List<Module> {
    return listOf(
      profileViewModelModule
    )
  }
}