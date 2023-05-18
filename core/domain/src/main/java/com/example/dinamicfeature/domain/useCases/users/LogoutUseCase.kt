package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class LogoutUseCase(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(): Boolean =
    userRepository.logout()
}