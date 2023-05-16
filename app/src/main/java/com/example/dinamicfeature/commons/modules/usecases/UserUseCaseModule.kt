package com.example.dinamicfeature.commons.modules.usecases

import com.example.dinamicfeature.domain.useCases.users.CreateUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LoginUserUseCase
import org.koin.dsl.module

val userUseCaseModule = module {

  single { CreateUserUseCase(get()) }
  single { LoginUserUseCase(get()) }

}