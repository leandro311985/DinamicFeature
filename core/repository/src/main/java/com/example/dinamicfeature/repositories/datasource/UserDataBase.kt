package com.example.dinamicfeature.repositories.datasource

import com.example.dinamicfeature.domain.models.UserFirebase
import io.paperdb.Paper


class UserDataBase {

  fun saveUserData(id: String, user: UserFirebase?) {
    Paper.book()?.write(id, user)
  }

  fun getUser(id: String): Boolean? {
    return Paper.book().read(id, null)
  }
}