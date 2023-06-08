package com.example.dinamicfeature.profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.baseApp.constants.Constants.DATA_USER
import com.example.dinamicfeature.baseApp.constants.Constants.SAVE_PHOTO
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPhotoUseCase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
  private val getDataUseCase: GetDataUseCase,
  private val getPhotoUseCase: GetPhotoUseCase
) : BaseViewModel() {
  private lateinit var firebaseStorage: FirebaseStorage

  private val _user = MutableSharedFlow<UserFirebase>()
  val user = _user.asSharedFlow()

  private val _photoUser = MutableSharedFlow<String>()
  val photoUser = _photoUser.asSharedFlow()


  fun getDataUser() {
    viewModelScope.launch {
      val result = getDataUseCase(DATA_USER)
      delay(500)
      if (result != null) {
        _user.emit(result)
      }
    }
  }

  fun getPhoto() {
    viewModelScope.launch {
      val result = getPhotoUseCase(SAVE_PHOTO)
      _photoUser.emit(result ?: "")
    }
  }

}