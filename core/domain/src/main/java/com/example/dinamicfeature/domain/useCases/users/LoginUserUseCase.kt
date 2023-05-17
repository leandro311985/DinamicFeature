package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.baseApp.common.UiState
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class LoginUserUseCase(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(email:String,password:String,result: (UiState<Boolean>) -> Unit) =
    userRepository.loginUser(email, password,result)
}