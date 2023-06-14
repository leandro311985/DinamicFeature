package com.example.dinamicfeature.repositories.datasource

import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.models.UserFirebase
import io.paperdb.Paper


class UserDataBase {

  fun saveUserData(id: String, user: UserFirebase?) {
    Paper.book()?.write(id, user)
  }

  fun savePhoto(id: String, photo: String) {
    Paper.book()?.write(id, photo)
  }

  fun saveRegister(id: String, photo: ProfileBasicDataUsers?) {
    Paper.book()?.write(id, photo)
  }

  fun getRegister(id: String): ProfileBasicDataUsers? {
    return Paper.book().read(id, null)
  }

  fun getUser(id: String): UserFirebase? {
    return Paper.book().read(id, null)
  }

  fun getPhoto(id: String): String? {
    return Paper.book().read(id, "")
  }
}