package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetPeopleFakeUseCase(
  private val userRepository: IUserRepository
) {

  suspend operator fun invoke(): PersonsFake? =
    userRepository.getPeopleFake()
}