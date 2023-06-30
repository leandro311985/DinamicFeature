package com.example.dinamicfeature.domain.useCases.main

import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.repositories.main.IMainRepository

class GetMyListUseCase(
  private val mainRepository: IMainRepository
) {
  suspend operator fun invoke(): MutableList<MyPersonsFake?> =
    mainRepository.getMyList()
}