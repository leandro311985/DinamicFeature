package com.example.dinamicfeature.presentation.explore

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

class ExploreViewModel(private val getPersonUseCase: GetPersonHomeUseCase,) : BaseViewModel() {

  private val _listHome = MutableSharedFlow<List<PersonsFakeHome>>()
  val listHome = _listHome.asSharedFlow()


  fun getList() {
    viewModelScope.launch {
      val result = getPersonUseCase()
      delay(1000)
      _listHome.emit(result as List<PersonsFakeHome>)
    }
  }
}