package com.example.dinamicfeature.repositories.datasource

import com.example.dinamicfeature.domain.models.LocationData
import io.paperdb.Paper


class MainDataBase {

  fun saveLocation(id: String, user: LocationData?) {
    Paper.book()?.write(id, user)
  }

  fun getLocation(id: String): LocationData {
    return Paper.book().read(id,LocationData())
  }
}