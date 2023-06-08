package com.example.dinamicfeature.login.presentation.splash

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.useCases.users.IsLoginUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
  private val isLoginUserUseCase: IsLoginUserUseCase,
) : BaseViewModel() {
  private val _isLogged = MutableSharedFlow<Boolean>()
  val isLogged = _isLogged.asSharedFlow()

  fun isLogged() {
    viewModelScope.launch {
      val result = isLoginUserUseCase()
      delay(500)
      _isLogged.emit(result)
    }
  }
}