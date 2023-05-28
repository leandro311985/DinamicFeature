package com.example.dinamicfeature.domain.useCases.users

import android.net.Uri
import com.example.dinamicfeature.domain.repositories.users.IUserRepository

class SavePhotoUseCase(
  private val userRepository: IUserRepository
) {
  suspend operator fun invoke(photo: Uri?) =
    userRepository.savePhoto(photo.toString())
}