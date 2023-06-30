package com.example.dinamicfeature.repositories.repository.main

import android.content.Context
import com.example.dinamicfeature.baseApp.constants.Constants
import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.models.MyPersonsFake
import com.example.dinamicfeature.domain.repositories.main.IMainRepository
import com.example.dinamicfeature.repositories.datasource.MainDataBase

class MainRepository(
  private val context: Context,
  private val database: MainDataBase

) : IMainRepository {

  override suspend fun saveLocation(location: LocationData): Boolean {
    database.saveLocation(Constants.SAVE_LOCATION, location)
    return true
  }

  override suspend fun getLocation(): LocationData {
    return database.getLocation(Constants.SAVE_LOCATION)
  }

  override suspend fun getMyList(): MutableList<MyPersonsFake?> {
    return database.getMyListFake(Constants.SAVE_MY_LIST_KEY)
  }

  override suspend fun saveMyList(list: MutableList<MyPersonsFake?>): Boolean {
    database.saveMyListFake(Constants.SAVE_MY_LIST_KEY, list)
    return true
  }

}