package com.example.dinamicfeature.domain.useCases.main

import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.repositories.main.IMainRepository

class SaveLocationUseCase(
  private val mainRepository: IMainRepository
) {
  suspend operator fun invoke(location: LocationData) =
    mainRepository.saveLocation(location)
}