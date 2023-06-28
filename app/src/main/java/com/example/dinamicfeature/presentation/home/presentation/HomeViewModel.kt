package com.example.dinamicfeature.presentation.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.dinamicfeature.domain.useCases.users.GetPersonHomeUseCase
import com.example.dinamicfeature.domain.useCases.users.GetPersonUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
  private val getPersonHomeUseCase: GetPersonHomeUseCase,
  private val getPersonUseCase: GetPersonUseCase,
) : BaseViewModel() {

  private val _listHomeGrade = MutableSharedFlow<List<PersonsFake>>()
  val listHomeGrade = _listHomeGrade.asSharedFlow()

  private val _listHomeNormal = MutableSharedFlow<List<PersonsFake>>()
  val listHomeNormal = _listHomeNormal.asSharedFlow()


  fun getList(typeVisual: TypeVisual) {
    viewModelScope.launch {
      val result = getPersonUseCase()
      delay(500)
      when(typeVisual){
        TypeVisual.GRADE -> _listHomeGrade.emit(result as List<PersonsFake>)
        TypeVisual.NORMAL -> _listHomeNormal.emit(result as List<PersonsFake>)
      }
    }
  }

}