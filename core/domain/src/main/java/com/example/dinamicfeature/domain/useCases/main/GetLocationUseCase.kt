package com.example.dinamicfeature.domain.useCases.main

import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.repositories.main.IMainRepository

class GetLocationUseCase(
  private val mainRepository: IMainRepository
) {
  suspend operator fun invoke() : LocationData =
    mainRepository.getLocation()
}