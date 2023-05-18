package com.example.dinamicfeature.domain.models

data class UserFirebase(
  var id: String = "",
  val first_name: String = "",
  val last_name: String = "",
  val job_title: String = "",
  val email: String = "",
)
