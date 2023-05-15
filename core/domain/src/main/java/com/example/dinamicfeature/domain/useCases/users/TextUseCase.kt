package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class TextUseCase(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(): String =
    userRepository.TextUser()
}