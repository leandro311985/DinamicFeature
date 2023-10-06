package com.example.dinamicfeature.presentation.journey.presentation

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.models.PhysicalData
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.models.ProfileGeneralData
import com.example.dinamicfeature.domain.useCases.users.GetRegisterGeneralDataUseCase
import com.example.dinamicfeature.domain.useCases.users.GetRegisterPhysicalUseCase
import com.example.dinamicfeature.domain.useCases.users.GetRegisterUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveRegisterProfileGeneralDataUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveRegisterProfilePhysicalDataUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveRegisterUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
  private val saveRegisterUseCase: SaveRegisterUseCase,
  private val saveRegisterProfileGeneralDataUseCase: SaveRegisterProfileGeneralDataUseCase,
  private val saveRegisterProfilePhysicalDataUseCase: SaveRegisterProfilePhysicalDataUseCase,
  private val getRegisterUseCase: GetRegisterUseCase,
  private val getRegisterPhysicalUseCase: GetRegisterPhysicalUseCase,
  private val getRegisterGeneralDataUseCase: GetRegisterGeneralDataUseCase,
) : BaseViewModel() {

  private val _success = MutableSharedFlow<Boolean>()
  val success = _success.asSharedFlow()

  private val _successGeneralData = MutableSharedFlow<Boolean>()
  val successGeneralData = _successGeneralData.asSharedFlow()

  private val _successPhysical = MutableSharedFlow<Boolean>()
  val successPhysical = _successPhysical.asSharedFlow()

  private val _getDataRegister = MutableSharedFlow<ProfileBasicDataUsers?>()
  val getDataRegister = _getDataRegister.asSharedFlow()

  private val _getDataPhysicalData = MutableSharedFlow<PhysicalData?>()
  val getDataPhysicalData = _getDataPhysicalData.asSharedFlow()

  private val _getDataProfileGeneralData = MutableSharedFlow<ProfileGeneralData?>()
  val getDataProfileGeneralData = _getDataProfileGeneralData.asSharedFlow()

  fun saveRegisterDb(register: ProfileBasicDataUsers) {
    viewModelScope.launch {
      val result = saveRegisterUseCase(register)
      _success.emit(result)
    }
  }

  fun saveRegisterDbProfileGeneralData(profileGeneralData: ProfileGeneralData) {
    viewModelScope.launch {
      val result = saveRegisterProfileGeneralDataUseCase(profileGeneralData)
      _successGeneralData.emit(result)
    }
  }

  fun saveRegisterDbPhysicalData(register: PhysicalData) {
    viewModelScope.launch {
      val result = saveRegisterProfilePhysicalDataUseCase(register)
      _successPhysical.emit(result)
    }
  }

  fun getRegisterUser() {
    viewModelScope.launch {
      delay(500)
      val result = getRegisterUseCase()
      _getDataRegister.emit(result)
    }
  }

  fun getRegisterUserGeneralData() {
    viewModelScope.launch {
      delay(1000)
      val result = getRegisterGeneralDataUseCase()
      _getDataProfileGeneralData.emit(result)
    }
  }

  fun getRegisterUserPhysicalData() {
    viewModelScope.launch {
      delay(1000)
      val result = getRegisterPhysicalUseCase()
      _getDataPhysicalData.emit(result)
    }
  }
}