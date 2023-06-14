package com.example.dinamicfeature.domain.repositories.users

import com.example.dinamicfeature.baseApp.commons.UiState
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.ProfileBasicDataUsers
import com.example.dinamicfeature.domain.models.UserFirebase

interface IUserRepository {

  suspend fun registerUser(email: String, password: String, user: UserFirebase, name:String):UiState<String>
  suspend fun updateUserInfo(user: UserFirebase, result: (UiState<String>) -> Unit)
  suspend fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
  suspend fun logout():Boolean
  suspend fun loginUser(email: String, password: String, result: (UiState<Boolean>) -> Unit)
  suspend fun isLoginUser():Boolean
  suspend fun savePhoto(photo:String):Boolean
  suspend fun saveRegister(register: ProfileBasicDataUsers?):Boolean
  suspend fun getRegister():ProfileBasicDataUsers?
  suspend fun getPhoto(id:String):String?
  suspend fun getDataUser(key:String):UserFirebase?
  suspend fun getListPerson():List<PersonsFake?>

}