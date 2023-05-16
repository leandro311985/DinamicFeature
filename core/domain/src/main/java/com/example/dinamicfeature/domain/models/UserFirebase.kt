package com.example.dinamicfeature.domain.models

import android.net.Uri

data class UserFirebase(
  var name: String?,
  var email: String?,
  var photoUrl: Uri?,
  var emailVerified: Boolean?,
  var uid: String?
)
