package com.example.dinamicfeature.login

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.common.BaseViewModel
import com.example.dinamicfeature.baseApp.common.UiState
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

  private val _user = MutableSharedFlow<Boolean>()
  val user = _user.asSharedFlow()

  var state : Boolean = false

  fun login(email:String,password: String) {
    viewModelScope.launch {
       loginUserUseCase(email,password){
         when(it){
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
}