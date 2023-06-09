package com.example.dinamicfeature.di.modules.firebase

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module
@SuppressLint("MissingPermission")

val firebaseModule = module {

  single { FirebaseAuth.getInstance() }
}

