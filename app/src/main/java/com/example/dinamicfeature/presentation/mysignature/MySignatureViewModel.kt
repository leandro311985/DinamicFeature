package com.example.dinamicfeature.presentation.mysignature

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.useCases.users.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MySignatureViewModel(
  private val logoutUseCase: LogoutUseCase,
) : BaseViewModel() {

  private val _logout = MutableSharedFlow<Boolean>()
  val logout = _logout.asSharedFlow()


  fun logout() {
    viewModelScope.launch {
      var result = logoutUseCase()
      _logout.emit(result)
    }
  }
}