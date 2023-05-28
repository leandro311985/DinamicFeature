package com.example.dinamicfeature.home

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.baseApp.constants.Constants.DATA_USER
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.useCases.users.GetDataUseCase
import com.example.dinamicfeature.domain.useCases.users.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
  private val logoutUseCase: LogoutUseCase,
  private val getDataUseCase: GetDataUseCase
): BaseViewModel() {

  private val _logout = MutableSharedFlow<Boolean>()
  val logout = _logout.asSharedFlow()

  private val _user = MutableSharedFlow<UserFirebase>()
  val user = _user.asSharedFlow()

  fun logout(){
    viewModelScope.launch {
      var result = logoutUseCase()
      _logout.emit(result)
    }
  }

  fun getDataUser(){
    viewModelScope.launch {
      var user = getDataUseCase(DATA_USER)
      user?.let {
        _user.emit(user)
      }
    }
  }
}