package com.example.dinamicfeature.repositories.datasource

import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.PhysicalData
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.models.ProfileGeneralData
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

  fun savePersonFake(id: String, personsFake: PersonsFake?) {
    Paper.book()?.write(id, personsFake)
  }

  fun saveListKeyPersonFake(id: String, personsFake: List<PersonsFake>) {
    Paper.book()?.write(id, personsFake)
  }

  fun getPersonFake(id: String) : PersonsFake?{
    return Paper.book().read(id, null)
  }
  fun saveRegisterProfileGeneralData(id: String, photo: ProfileGeneralData?) {
    Paper.book()?.write(id, photo)
  }
  fun saveRegisterProfilePhysicalData(id: String, photo: PhysicalData?) {
    Paper.book()?.write(id, photo)
  }

  fun getRegister(id: String): ProfileBasicDataUsers? {
    return Paper.book().read(id, null)
  }

  fun getListKey(id: String): List<PersonsFake> {
    return Paper.book().read(id, listOf())
  }
  fun getRegisterProfileGeneralData(id: String): ProfileGeneralData? {
    return Paper.book().read(id, null)
  }
  fun getRegisterProfilePhysicalData(id: String): PhysicalData? {
    return Paper.book().read(id, null)
  }

  fun getUser(id: String): UserFirebase? {
    return Paper.book().read(id, null)
  }

  fun getPhoto(id: String): String? {
    return Paper.book().read(id, "")
  }
}