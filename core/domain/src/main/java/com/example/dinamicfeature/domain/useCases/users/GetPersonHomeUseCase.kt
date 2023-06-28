package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetPersonHomeUseCase(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(): List<PersonsFakeHome?> =
    userRepository.getListPersonHome()
}