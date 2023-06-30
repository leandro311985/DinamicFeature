package com.example.dinamicfeature.domain.repositories.main

import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.models.MyPersonsFake

interface IMainRepository {


  suspend fun saveLocation(location: LocationData):Boolean
  suspend fun getLocation():LocationData
  suspend fun getMyList():MutableList<MyPersonsFake?>
  suspend fun saveMyList(list: MutableList<MyPersonsFake?>):Boolean


}