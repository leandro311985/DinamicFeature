package com.example.dinamicfeature.domain.useCases.users

import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class GetPhotoUseCase(
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(id:String) :String? =
    userRepository.getPhoto(id)
}