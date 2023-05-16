package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class CreateUserUseCase(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(email:String,password:String): Boolean =
    userRepository.createUser(email, password)
}