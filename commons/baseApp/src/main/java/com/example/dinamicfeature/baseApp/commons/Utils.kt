package com.example.dinamicfeature.baseApp.commons

fun String.isValidEmail() =
  isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

