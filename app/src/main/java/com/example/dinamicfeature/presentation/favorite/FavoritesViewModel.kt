package com.example.dinamicfeature.presentation.favorite

import androidx.lifecycle.viewModelScope
import com.example.dinamicfeature.baseApp.commons.BaseViewModel
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.dinamicfeature.domain.useCases.users.GetPersonHomeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val getPersonHomeUseCase: GetPersonHomeUseCase,
) : BaseViewModel() {

  private val _listHome = MutableSharedFlow<List<PersonsFakeHome>>()
  val listHome = _listHome.asSharedFlow()


  fun getList() {
    viewModelScope.launch {
      val result = getPersonHomeUseCase()
      delay(500)
      _listHome.emit(result as List<PersonsFakeHome>)
    }
  }
}