package com.example.dinamicfeature.domain.repositories.users

import com.example.dinamicfeature.domain.models.UserFirebase

interface IUserRepository {

  suspend fun createUser(email:String,password:String): Boolean
  suspend fun signInUser(email:String,password:String): UserFirebase

}