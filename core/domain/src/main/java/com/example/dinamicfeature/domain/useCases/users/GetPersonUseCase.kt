package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetPersonUseCase(
  private val userRepository: IUserRepository,
  private val data: GetRegisterGeneralDataUseCase
) {
  suspend operator fun invoke(): List<PersonsFake?> {
    val dataPreference = data()
    val result = userRepository.getListPerson()
    val dats = if (dataPreference?.type == null) "femi" else dataPreference.type
    return result.filter { it?.type == dats }
  }

}