package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.baseApp.common.UiState
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class CreateUserUseCase(
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(email:String, password:String, user: UserFirebase, name:String) : UiState<String> =
    userRepository.registerUser(email, password,user,name)
}