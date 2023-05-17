package com.example.dinamicfeature.repositories.repository.user

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.dinamicfeature.baseApp.common.UiState
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.models.erros.ApiError
import com.example.dinamicfeature.domain.repositories.users.IUserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.JsonAdapter

class UserRepository(
  private val context: Context,
  private val auth: FirebaseAuth,
) : IUserRepository {


  override suspend fun createUser(email: String, password: String): Boolean {
    val result = auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
      if (task.isSuccessful) {
        Log.d(TAG, "createUserWithEmail:success")
        val user = auth.currentUser
      } else {
        Log.w(TAG, "createUserWithEmail:failure", task.exception)

      }
    }
    return result.isSuccessful
  }

  override suspend fun loginUser(email: String, password: String, result: (UiState<Boolean>) -> Unit) {
    auth.signInWithEmailAndPassword(email,password)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
            if (task == null){
              result.invoke(UiState.Failure("Failed to store local session"))
            }else{
              result.invoke(UiState.Success(true))
            }
        }
      }.addOnFailureListener {
        result.invoke(UiState.Failure("Authentication failed, Check email and password"))
      }
  }

  private fun getJson(rawResource: Int): String {
    return context.resources.openRawResource(rawResource)
      .bufferedReader().use { it.readText() }
  }

}