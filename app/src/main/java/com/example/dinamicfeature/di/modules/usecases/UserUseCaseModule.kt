package com.example.dinamicfeature.di.modules.usecases

import com.example.dinamicfeature.domain.useCases.users.CreateUserUseCase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.GetListLikeUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPeopleFakeUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPersonHomeUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPersonUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPhotoUseCase
import com.example.dinamicfeature.domain.useCases.users.GetRegisterGeneralDataUseCase
import com.example.dinamicfeature.domain.useCases.users.GetRegisterPhysicalUseCase
import com.example.dinamicfeature.domain.useCases.users.GetRegisterUseCase
import com.example.dinamicfeature.domain.useCases.users.IsLoginUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LoginUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LogoutUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveListLikeUseCase
import com.example.dinamicfeature.domain.useCases.users.SavePeopleFakeUseCase
import com.example.dinamicfeature.domain.useCases.users.SavePhotoUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveRegisterProfileGeneralDataUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveRegisterProfilePhysicalDataUseCase
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
  single { GetRegisterPhysicalUseCase(get()) }
  single { GetRegisterGeneralDataUseCase(get()) }
  single { SaveRegisterUseCase(get()) }
  single { SavePeopleFakeUseCase(get()) }
  single { GetPersonUseCase(get(),get()) }
  single { GetPersonHomeUseCase(get()) }
  single { SaveListLikeUseCase(get()) }
  single { GetPeopleFakeUseCase(get()) }
  single { GetListLikeUseCase(get()) }
  single { SaveRegisterProfileGeneralDataUseCase(get()) }
  single { SaveRegisterProfilePhysicalDataUseCase(get()) }

}