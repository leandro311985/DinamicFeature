package com.example.dinamicfeature.repositories.repository.user

import android.content.Context
import com.example.dinamicfeature.domain.models.erros.ApiError
import com.example.dinamicfeature.domain.repositories.users.IUserRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class UserRepository(
  private val context: Context,
  private val jsonAdapter: JsonAdapter<ApiError>,
  moshi: Moshi
) : IUserRepository {

  override suspend fun TextUser(): String {

    val text = "texto repository"

    return text
  }


  private fun getJson(rawResource: Int): String {
    return context.resources.openRawResource(rawResource)
      .bufferedReader().use { it.readText() }
  }

}