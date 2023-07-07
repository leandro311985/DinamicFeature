package com.example.dinamicfeature.domain.models

data class ProfileBasicDataUsers(
  var nickname: String? = null,
  var selectedDate: String? = null,
  var country: String? = null,
  var city: String? = null,
  var street: String? = null,
  var countFilledFields: Int? = 0
)

data class PhysicalData(
  var height: String? = null,
  var bodyType: List<String>? = null,
  var yourAppearance: List<String>? = null,
  var countFilledFields: Int? = 0
)

data class ProfileGeneralData(
  var type:String = "",
  var objective: List<String>? = null,
  var countFilledFields: Int? = 0

)
