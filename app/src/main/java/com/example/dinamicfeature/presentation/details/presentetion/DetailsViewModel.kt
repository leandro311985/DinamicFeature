package com.example.dinamicfeature.presentation.details.presentetion

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.useCases.users.GetPeopleFakeUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPersonUseCase
import com.example.dinamicfeature.domain.useCases.users.SaveListLikeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
  private val getPersonFakeUseCase: GetPeopleFakeUseCase,
  private val getPersonUseCase: GetPersonUseCase,
  private val saveListFakeDataUseCase: SaveListLikeUseCase
  ) : BaseViewModel() {

  private val _getPersonFake = MutableSharedFlow<PersonsFake?>()
  val getPersonFake = _getPersonFake.asSharedFlow()

  private val _listPerson = MutableSharedFlow<List<PersonsFake?>>()
  val listPerson = _listPerson.asSharedFlow()

  fun getPersonFake() {
    viewModelScope.launch {
      val result = getPersonFakeUseCase()
      delay(1000)
      _getPersonFake.emit(result)
    }
  }

  fun getListPerson() {
    viewModelScope.launch {
      val result = getPersonUseCase()
      delay(1000)
      _listPerson.emit(result)
    }
  }

  fun saveLikeList(personsFake: List<PersonsFake>) {
    viewModelScope.launch {
      delay(1000)
      saveListFakeDataUseCase(personsFake)
    }
  }
}