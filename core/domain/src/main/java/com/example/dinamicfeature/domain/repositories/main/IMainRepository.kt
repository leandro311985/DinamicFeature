package com.example.dinamicfeature.domain.repositories.main

import com.example.dinamicfeature.domain.models.LocationData

interface IMainRepository {


  suspend fun saveLocation(location: LocationData):Boolean
  suspend fun getLocation():LocationData


}