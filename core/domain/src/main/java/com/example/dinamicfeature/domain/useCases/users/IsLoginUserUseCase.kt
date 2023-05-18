package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class IsLoginUserUseCase(
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke() : Boolean =
    userRepository.isLoginUser()
}