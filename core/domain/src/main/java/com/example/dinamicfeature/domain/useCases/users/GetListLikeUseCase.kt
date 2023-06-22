package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetListLikeUseCase (
  private val userRepository: IUserRepository
) {

    suspend operator fun invoke(): List<PersonsFake?> =
    userRepository.getListKey()
}