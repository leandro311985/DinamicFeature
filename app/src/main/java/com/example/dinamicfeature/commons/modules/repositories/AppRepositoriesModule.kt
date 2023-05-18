package com.example.dinamicfeature.commons.modules.repositories

import com.example.dinamicfeature.domain.repositories.users.IUserRepository
import com.example.dinamicfeature.repositories.repository.user.UserRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val userRepositoryModule = module {

  single {
    UserRepository(
      get(),
      get(),
      get()
    )
  } bind IUserRepository::class
}
