package com.example.dinamicfeature.repositories.repository.user

import android.content.Context
import com.example.dinamicfeature.baseApp.commons.UiState
import com.example.dinamicfeature.baseApp.constants.Constants
import com.example.dinamicfeature.baseApp.constants.Constants.DATA_USER
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.domain.repositories.users.IUserRepository
import com.example.dinamicfeature.repositories.datasource.UserDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class UserRepository(
  private val context: Context,
  private val auth: FirebaseAuth,
  private val database: UserDataBase
  ) : IUserRepository {

  private val userEmpty = UserFirebase()
  override suspend fun registerUser(
    email: String,
    password: String,
    user: UserFirebase, name:String
  ): UiState<String> {
    var result = UiState.Success("")
    auth.createUserWithEmailAndPassword(email,password)
      .addOnCompleteListener {
        if (it.isSuccessful){
          val user = UserFirebase(it.result.user?.uid ?: "",name,
          "","",it.result.user?.email ?: "")
          database.saveUserData(DATA_USER,user)
          result = UiState.Success("success")
        }else{
          try {
            throw it.exception ?: java.lang.Exception("Autenticação inválida")
          } catch (e: FirebaseAuthWeakPasswordException) {
           UiState.Success("Falha na autenticação, a senha deve ter pelo menos 6 caracteres")
          } catch (e: FirebaseAuthInvalidCredentialsException) {
            UiState.Success("Falha na autenticação, e-mail inválido inserido")
          } catch (e: FirebaseAuthUserCollisionException) {
            UiState.Success("Falha na autenticação, e-mail já registrado.")
          } catch (e: Exception) {
          UiState.Success(e.message)
          }
        }
      }
      .addOnFailureListener {
          result = UiState.Success(it.message.toString())
      }
    return result
  }

  override suspend fun updateUserInfo(user: UserFirebase, result: (UiState<String>) -> Unit) {

  }

  override suspend fun forgotPassword(email: String, result: (UiState<String>) -> Unit) {
    auth.sendPasswordResetEmail(email)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          result.invoke(UiState.Success("Email has been sent"))

        } else {
          result.invoke(UiState.Failure(task.exception?.message))
        }
      }.addOnFailureListener {
        result.invoke(UiState.Failure("Authentication failed, Check email"))
      }
  }

  override suspend fun logout():Boolean {
    auth.signOut()
    database.saveUserData(DATA_USER,userEmpty)
    return true
  }

  override suspend fun loginUser(email: String, password: String, result: (UiState<Boolean>) -> Unit) {
    auth.signInWithEmailAndPassword(email,password)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          val user = UserFirebase(task.result.user?.uid ?: "",task.result.user?.displayName?:"",
            "","",task.result.user?.email ?: "")
          database.saveUserData(DATA_USER,user)
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

  override suspend fun isLoginUser():Boolean {
    val currentUser = auth.currentUser
    return currentUser != null
  }

  override suspend fun savePhoto(photo: String) : Boolean {
    database.savePhoto(Constants.SAVE_PHOTO,photo)
    return true
  }

  override suspend fun getPhoto(id: String): String? {
    return database.getPhoto(id)
  }

  override suspend fun getDataUser(key: String): UserFirebase? {
   return database.getUser(key)
  }

  private fun getJson(rawResource: Int): String {
    return context.resources.openRawResource(rawResource)
      .bufferedReader().use { it.readText() }
  }

}