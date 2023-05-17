package com.example.dinamicfeature.domain.repositories.users

import com.example.dinamicfeature.baseApp.common.UiState

interface IUserRepository {

  suspend fun createUser(email:String,password:String): Boolean
  suspend fun loginUser(email: String, password: String, result: (UiState<Boolean>) -> Unit)

}