package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetPersonUseCase(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(): List<PersonsFake?> =
    userRepository.getListPerson()
}