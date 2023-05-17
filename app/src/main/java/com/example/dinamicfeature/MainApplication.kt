package com.example.dinamicfeature

import android.app.Application
import com.example.dinamicfeature.commons.modules.ModuleInitializer
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    init()
  }

  private fun init() {
    FirebaseApp.initializeApp(this)
    setupKoin()

  }

  private fun setupKoin() {
    startKoin {
//      androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
      androidLogger()
      androidContext(this@MainApplication)
      modules(
        ModuleInitializer.modules
      )
    }
  }

}