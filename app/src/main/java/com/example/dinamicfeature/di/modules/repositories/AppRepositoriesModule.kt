package com.example.dinamicfeature.di.modules.repositories

import com.example.dinamicfeature.domain.repositories.users.IUserRepository
import com.example.dinamicfeature.repositories.repository.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val userRepositoryModule = module {
  factory<IUserRepository> {
    UserRepository(androidContext(), get(), get(),get())
  }

}
