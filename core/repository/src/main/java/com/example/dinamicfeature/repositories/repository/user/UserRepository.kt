package com.example.dinamicfeature.repositories.repository.user

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.models.erros.ApiError
import com.example.dinamicfeature.domain.repositories.users.IUserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.JsonAdapter

class UserRepository(
  private val context: Context
) : IUserRepository {

  private lateinit var auth: FirebaseAuth

  override suspend fun createUser(email: String, password: String): Boolean {
    auth = Firebase.auth

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

  override suspend fun signInUser(email: String, password: String): UserFirebase {
    var userFirebase = UserFirebase(null, null, null, null, null)
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
      if (task.isSuccessful) {
        Log.d(TAG, "signInWithEmail:success")
        val user = auth.currentUser
        userFirebase = UserFirebase(user?.displayName, user?.email, user?.photoUrl, user?.isEmailVerified, user?.uid)
      } else {
        Log.w(TAG, "signInWithEmail:failure", task.exception)
      }
    }
    return userFirebase
  }


  private fun getJson(rawResource: Int): String {
    return context.resources.openRawResource(rawResource)
      .bufferedReader().use { it.readText() }
  }

}