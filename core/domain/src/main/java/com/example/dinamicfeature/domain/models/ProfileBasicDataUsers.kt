package com.example.dinamicfeature.domain.models

data class ProfileBasicDataUsers(
  var nickname: String? = null,
  var selectedDate: String? = null,
  var country: String? = null,
  var city: String? = null,
  var street: String? = null,
  var physical: PhysicalData? = null,
  var profileGeneralData: ProfileGeneralData? = null
)

data class PhysicalData(
  var height: String? = null,
  var bodyType: String? = null,
  var yourAppearance: String? = null
)

data class ProfileGeneralData(
  var preferences: String? = null,
  var objective: String? = null
)
