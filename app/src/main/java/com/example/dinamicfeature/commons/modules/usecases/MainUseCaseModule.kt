package com.example.dinamicfeature.commons.modules.usecases

import com.example.dinamicfeature.domain.useCases.main.GetLocationUseCase
import com.example.dinamicfeature.domain.useCases.main.SaveLocationUseCase
import org.koin.dsl.module

val mainUseCaseModule = module {

  single { SaveLocationUseCase(get()) }
  single { GetLocationUseCase(get()) }


}