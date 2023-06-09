package com.example.dinamicfeature.di.modules.db

import com.example.dinamicfeature.repositories.datasource.MainDataBase
import com.example.dinamicfeature.repositories.datasource.UserDataBase
import org.koin.dsl.module

val databaseModule = module {
  factory { UserDataBase() }
  factory { MainDataBase() }
}