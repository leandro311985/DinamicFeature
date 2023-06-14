package com.example.dinamicfeature.di.modules.usecases

import com.example.dinamicfeature.domain.useCases.users.CreateUserUseCase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPersonUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPhotoUseCase
import com.example.dinamicfeature.domain.useCases.users.GetRegisterUseCase
import com.example.dinamicfeature.domain.useCases.users.IsLoginUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LoginUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LogoutUseCase
import com.example.dinamicfeature.domain.useCases.users.SavePhotoUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveRegisterUseCase
import org.koin.dsl.module

val userUseCaseModule = module {

  single { CreateUserUseCase(get()) }
  single { LoginUserUseCase(get()) }
  single { IsLoginUserUseCase(get()) }
  single { LogoutUseCase(get()) }
  single { GetDataUseCase(get()) }
  single { SavePhotoUseCase(get()) }
  single { GetPhotoUseCase(get()) }
  single { GetRegisterUseCase(get()) }
  single { SaveRegisterUseCase(get()) }
  single { GetPersonUseCase(get()) }

}