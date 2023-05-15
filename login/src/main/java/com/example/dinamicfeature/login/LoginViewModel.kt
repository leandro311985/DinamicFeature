package com.example.dinamicfeature.login

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.common.BaseViewModel
import com.example.dinamicfeature.domain.useCases.users.TextUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
  private val textUseCase: TextUseCase,
): BaseViewModel() {

  private val _user = MutableSharedFlow<String>()
  val user = _user.asSharedFlow()

  fun getUser() {
    viewModelScope.launch {
      val result = textUseCase()
      delay(1000)
      _user.emit(result)
    }
  }
}