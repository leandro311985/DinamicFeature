package com.example.dinamicfeature.domain.models

import java.io.Serializable

data class PersonsFake(
  var id: String,
  var image: List<String>,
  var name: String,
  var end: String,
  var type: String,
  var like: Boolean,
  var km: String
) : Serializable
