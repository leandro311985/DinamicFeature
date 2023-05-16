package com.example.dinamicfeature.login

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.common.BaseViewModel
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.users.CreateUserUseCase
import com.example.dinamicfeature.domain.useCases.users.LoginUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
  private val createUserUseCase: CreateUserUseCase,
  private val loginUserUseCase: LoginUserUseCase,
): BaseViewModel() {

  private val _user = MutableSharedFlow<UserFirebase>()
  val user = _user.asSharedFlow()

  fun login(email:String,password: String) {
    viewModelScope.launch {
      val result = loginUserUseCase(email,password)
      delay(500)
      _user.emit(result)
    }
  }
}