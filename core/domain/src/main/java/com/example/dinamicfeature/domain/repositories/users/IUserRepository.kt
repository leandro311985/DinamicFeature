package com.example.dinamicfeature.domain.repositories.users

import com.example.dinamicfeature.baseApp.commons.UiState
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.dinamicfeature.domain.models.PhysicalData
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.models.ProfileGeneralData
import com.example.dinamicfeature.domain.models.UserFirebase

interface IUserRepository {

  suspend fun registerUser(email: String, password: String, user: UserFirebase, name: String): UiState<String>
  suspend fun updateUserInfo(user: UserFirebase, result: (UiState<String>) -> Unit)
  suspend fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
  suspend fun logout(): Boolean
  suspend fun loginUser(email: String, password: String, result: (UiState<Boolean>) -> Unit)
  suspend fun isLoginUser(): Boolean
  suspend fun savePhoto(photo: String): Boolean
  suspend fun savePeopleFake(personsFake: PersonsFake): Boolean
  suspend fun getPeopleFake(): PersonsFake?
  suspend fun getListKey(): List<PersonsFake?>
  suspend fun saveRegister(register: ProfileBasicDataUsers?): Boolean
  suspend fun saveListKey(list: List<PersonsFake>): Boolean
  suspend fun saveRegisterPhysicalData(register: PhysicalData?): Boolean
  suspend fun saveRegisterProfileGeneralData(register: ProfileGeneralData?): Boolean
  suspend fun getRegister(): ProfileBasicDataUsers?
  suspend fun getRegisterProfileGeneralData(): ProfileGeneralData?
  suspend fun getRegisterPhysicalData(): PhysicalData?
  suspend fun getPhoto(id: String): String?
  suspend fun getDataUser(key: String): UserFirebase?
  suspend fun getListPerson(): List<PersonsFake?>
  suspend fun getListPersonHome(): List<PersonsFakeHome?>

}