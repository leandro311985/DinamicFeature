package com.example.dinamicfeature.di.modules.usecases

import com.example.dinamicfeature.domain.useCases.main.GetLocationUseCase
import com.example.dinamicfeature.domain.useCases.main.GetMyListUseCase
import com.example.dinamicfeature.domain.useCases.main.SaveLocationUseCase
import com.example.dinamicfeature.domain.useCases.main.SaveMyListFakeUseCase
import org.koin.dsl.module

val mainUseCaseModule = module {

  single { SaveLocationUseCase(get()) }
  single { GetLocationUseCase(get()) }
  single { GetMyListUseCase(get()) }
  single { SaveMyListFakeUseCase(get(),get()) }


}