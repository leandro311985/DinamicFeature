package com.example.dinamicfeature.presentation.details.presentetion

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.useCases.main.SaveMyListFakeUseCase
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
  private val saveListFakeDataUseCase: SaveMyListFakeUseCase
  ) : BaseViewModel() {

  private val _getPersonFake = MutableSharedFlow<List<PersonsFake>>()
  val getPersonFake = _getPersonFake.asSharedFlow()

  private val _listPerson = MutableSharedFlow<List<PersonsFake?>>()
  val listPerson = _listPerson.asSharedFlow()

  fun getPersonFake() {
    viewModelScope.launch {
      val result = getPersonUseCase()
      delay(500)
      _getPersonFake.emit(result as List<PersonsFake>)
    }
  }

  fun getListPerson() {
    viewModelScope.launch {
      val result = getPersonUseCase()
      delay(1000)
      _listPerson.emit(result)
    }
  }

  fun saveLikeList(personsFake: MyPersonsFake) {
    viewModelScope.launch {
      delay(500)
      saveListFakeDataUseCase(personsFake)
    }
  }
}