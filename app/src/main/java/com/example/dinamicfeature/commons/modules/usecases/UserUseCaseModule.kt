package com.example.dinamicfeature.commons.modules.usecases

import com.example.dinamicfeature.domain.useCases.users.TextUseCase
import org.koin.dsl.module

val userUseCaseModule = module {

  single { TextUseCase(get()) }

}