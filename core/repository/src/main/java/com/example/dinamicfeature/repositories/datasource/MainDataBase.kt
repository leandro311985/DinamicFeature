package com.example.dinamicfeature.repositories.datasource

import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.models.MyPersonsFake
import io.paperdb.Paper


class MainDataBase {

  private var listFake = mutableListOf<MyPersonsFake?>()

  fun saveLocation(id: String, user: LocationData?) {
    Paper.book()?.write(id, user)
  }

  fun getLocation(id: String): LocationData {
    return Paper.book().read(id, LocationData())
  }

  fun getMyListFake(id: String): MutableList<MyPersonsFake?> {
    return Paper.book().read(id, listFake)
  }

  fun saveMyListFake(id: String, list: MutableList<MyPersonsFake?>) {
    Paper.book()?.write(id, list)
  }
}