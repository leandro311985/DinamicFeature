package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.PhysicalData
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class SaveListLikeUseCase (
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(list: List<PersonsFake>) =
    userRepository.saveListKey(list)
}