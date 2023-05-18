package com.example.dinamicfeature.commons.modules.usecases

import com.example.dinamicfeature.domain.useCases.users.CreateUserUseCase
import com.example.dinamicfeature.domain.useCases.users.IsLoginUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LoginUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LogoutUseCase
import org.koin.dsl.module

val userUseCaseModule = module {

  single { CreateUserUseCase(get()) }
  single { LoginUserUseCase(get()) }
  single { IsLoginUserUseCase(get()) }
  single { LogoutUseCase(get()) }

}