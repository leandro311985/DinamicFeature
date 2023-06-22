package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PhysicalData
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetRegisterPhysicalUseCase(
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(): PhysicalData? =
    userRepository.getRegisterPhysicalData()
}