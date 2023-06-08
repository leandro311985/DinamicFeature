package com.example.dinamicfeature.login.presentation

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.baseApp.commons.UiState
import com.example.dinamicfeature.baseApp.constants.Constants
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.IsLoginUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LoginUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
  private val loginUserUseCase: LoginUserUseCase,
  private val isLoginUserUseCase: IsLoginUserUseCase,
  ) : BaseViewModel() {

  private val _user = MutableSharedFlow<Boolean>()
  val user = _user.asSharedFlow()

  private val _isLogged = MutableSharedFlow<Boolean>()
  val isLogged = _isLogged.asSharedFlow()

  var state: Boolean = false

  fun login(email: String, password: String) {
    viewModelScope.launch {
      loginUserUseCase(email, password) {
        when (it) {
          is UiState.Loading -> {

          }
          is UiState.Failure -> {
            state = false
          }
          is UiState.Success -> {
            state = true
          }
        }
      }

      delay(1000)
      _user.emit(state)
    }
  }

  fun isLogged() {
    viewModelScope.launch {
      val result = isLoginUserUseCase()
      delay(500)
      _isLogged.emit(result)
    }
  }

}