package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.ProfileGeneralData
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetRegisterGeneralDataUseCase(
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(): ProfileGeneralData? =
    userRepository.getRegisterProfileGeneralData()
}