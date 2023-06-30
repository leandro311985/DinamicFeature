package com.example.dinamicfeature.domain.useCases.main

import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.repositories.main.IMainRepository

class SaveMyListFakeUseCase(
  private val mainRepository: IMainRepository,
  private val getList: GetMyListUseCase

) {
  suspend operator fun invoke(myPersonsFake: MyPersonsFake): Boolean {
    val result = getList()
    val exists = result.any { person -> person?.personsFake?.id == myPersonsFake.personsFake.id }
    if (!exists) result.add(myPersonsFake)
    return mainRepository.saveMyList(result)

  }

}

