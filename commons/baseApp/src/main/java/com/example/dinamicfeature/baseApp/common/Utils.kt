package com.example.dinamicfeature.baseApp.common

fun String.isValidEmail() =
  isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

