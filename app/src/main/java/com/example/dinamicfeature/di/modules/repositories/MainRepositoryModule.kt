package com.example.dinamicfeature.di.modules.repositories

import com.example.dinamicfeature.domain.repositories.main.IMainRepository
import com.example.dinamicfeature.repositories.repository.main.MainRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainRepositoryModule = module {

  factory<IMainRepository> {
    MainRepository(androidContext(),get())
  }

}