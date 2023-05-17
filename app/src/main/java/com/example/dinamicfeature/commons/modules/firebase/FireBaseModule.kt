package com.example.dinamicfeature.commons.modules.firebase

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val networkModule = module {

  single {
    FirebaseAuth.getInstance()
  }
}