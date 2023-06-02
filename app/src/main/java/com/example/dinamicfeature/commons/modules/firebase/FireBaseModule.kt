package com.example.dinamicfeature.commons.modules.firebase

import android.annotation.SuppressLint
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
@SuppressLint("MissingPermission")

val firebaseModule = module {

  single { FirebaseAuth.getInstance() }
}

