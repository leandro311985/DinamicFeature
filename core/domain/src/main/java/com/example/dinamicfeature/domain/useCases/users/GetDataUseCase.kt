package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetDataUseCase (
  private val userRepository: IUserRepository
) {

    suspend operator fun invoke(key:String): UserFirebase? =
    userRepository.getDataUser(key)
}