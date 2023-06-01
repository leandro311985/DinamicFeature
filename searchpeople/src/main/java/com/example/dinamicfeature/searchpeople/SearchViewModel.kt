package com.example.dinamicfeature.searchpeople

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.baseApp.constants.Constants
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPhotoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel(
  private val getPhotoUseCase: GetPhotoUseCase,
  private val getDataUseCase: GetDataUseCase
) : BaseViewModel() {

  private val _userData = MutableSharedFlow<UserFirebase>()
  val userData = _userData.asSharedFlow()

  private val _photoUser = MutableSharedFlow<String>()
  val photoUser = _photoUser.asSharedFlow()

  fun getPhoto() {
    viewModelScope.launch {
      val result = getPhotoUseCase(Constants.SAVE_PHOTO)
      delay(1000)
      _photoUser.emit(result ?: "")
    }
  }

  fun getDataUser() {
    viewModelScope.launch {
      delay(2000)
      val result = getDataUseCase(Constants.DATA_USER)
      if (result != null) {
        _userData.emit(result)
      }
    }
  }
}