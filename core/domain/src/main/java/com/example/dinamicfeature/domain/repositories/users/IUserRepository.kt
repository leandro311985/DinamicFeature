package com.example.dinamicfeature.domain.repositories.users

interface IUserRepository {

  suspend fun TextUser(): String

}