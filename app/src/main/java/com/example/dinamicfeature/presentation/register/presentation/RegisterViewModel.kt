package com.example.dinamicfeature.presentation.register.presentation

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.useCases.users.GetRegisterUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveRegisterUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
  private val saveRegisterUseCase: SaveRegisterUseCase,
  private val getRegisterUseCase: GetRegisterUseCase,
) : BaseViewModel() {

  private val _success = MutableSharedFlow<Boolean>()
  val success = _success.asSharedFlow()

  private val _getDataRegister = MutableSharedFlow<ProfileBasicDataUsers?>()
  val getDataRegister = _getDataRegister.asSharedFlow()

  fun saveRegisterDb(register: ProfileBasicDataUsers) {
    viewModelScope.launch {
      val result = saveRegisterUseCase(register)
      _success.emit(result)
    }
  }

  fun getRegisterUser() {
    viewModelScope.launch {
      delay(1000)
      val result = getRegisterUseCase()
      _getDataRegister.emit(result)
    }
  }
}