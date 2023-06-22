package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PhysicalData
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class SaveRegisterProfilePhysicalDataUseCase  (
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(register: PhysicalData?) =
    userRepository.saveRegisterPhysicalData(register)
}