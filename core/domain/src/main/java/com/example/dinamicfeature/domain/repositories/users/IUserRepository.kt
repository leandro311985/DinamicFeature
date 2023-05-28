package com.example.dinamicfeature.domain.repositories.users

import android.net.Uri
import com.example.dinamicfeature.baseApp.commons.UiState
import com.example.dinamicfeature.domain.models.UserFirebase

interface IUserRepository {

  suspend fun registerUser(email: String, password: String, user: UserFirebase, name:String):UiState<String>
  suspend fun updateUserInfo(user: UserFirebase, result: (UiState<String>) -> Unit)
  suspend fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
  suspend fun logout():Boolean
  suspend fun loginUser(email: String, password: String, result: (UiState<Boolean>) -> Unit)
  suspend fun isLoginUser():Boolean
  suspend fun savePhoto(photo:String):Boolean
  suspend fun getPhoto(id:String):String?
  suspend fun getDataUser(key:String):UserFirebase?

}