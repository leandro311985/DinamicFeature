package com.example.dinamicfeature.searchpeople

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.baseApp.constants.Constants
import com.example.dinamicfeature.domain.useCases.users.GetPhotoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel(
  private val getPhotoUseCase: GetPhotoUseCase
) : BaseViewModel() {

  private val _photoUser = MutableSharedFlow<String>()
  val photoUser = _photoUser.asSharedFlow()

  fun getPhoto() {
    viewModelScope.launch {
      val result = getPhotoUseCase(Constants.SAVE_PHOTO)
      _photoUser.emit(result ?: "")
    }
  }
}