package com.example.dinamicfeature.domain.models

data class ProfileBasicDataUsers(
  var nickname: String? = null,
  var selectedDate: String? = null,
  var country: String? = null,
  var city: String? = null,
  var street: String? = null,
)

data class PhysicalData(
  var height: String? = null,
  var bodyType: List<String>? = null,
  var yourAppearance:List<String>? = null
)

data class ProfileGeneralData(
  var masculino: Boolean? = false,
  var feminino: Boolean? = false,
  var lgbtqa: Boolean? = false,
  var objective: List<String>? = null
)
