package com.example.dinamicfeature.createAccount

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.common.BaseViewModel
import com.example.dinamicfeature.baseApp.common.UiState
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.users.CreateUserUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CreateAccountViewModel(
  private val createUserUseCase: CreateUserUseCase
) : BaseViewModel() {

  private val _register = MutableSharedFlow<UiState<String>>()
  val register = _register.asSharedFlow()

  var state : Boolean? = null

  fun createAccount(email: String, password: String,name:String) {
    viewModelScope.launch {
     val result = createUserUseCase( email = email,
        password = password,
        user = UserFirebase(),
        name = name)

      delay(1000)
      _register.emit(result)
    }
  }
}