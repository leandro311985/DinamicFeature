package com.example.dinamicfeature.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.useCases.main.SaveLocationUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AppViewModel(
  private val saveLocationUseCase: SaveLocationUseCase,
) : com.example.dinamicfeature.baseApp.commons.BaseViewModel() {

  private val _locationSaved = MutableSharedFlow<Boolean>()
  val locationSaved = _locationSaved.asSharedFlow()
  fun saveLocationDb(location: LocationData) {
    viewModelScope.launch {
      val result = saveLocationUseCase(location)
      _locationSaved.emit(result)
    }
  }
}