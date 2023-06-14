package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class SaveRegisterUseCase(
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(register: ProfileBasicDataUsers?) =
    userRepository.saveRegister(register)
}