package com.example.dinamicfeature

import android.app.Application
import com.example.dinamicfeature.commons.modules.ModuleInitializer
import com.google.firebase.FirebaseApp
import io.paperdb.Paper
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
    setupPaperDb()

  }

  private fun setupKoin() {
    startKoin {
      androidLogger()
      androidContext(this@MainApplication)
      modules(
        ModuleInitializer.modules
      )
    }
  }

  private fun setupPaperDb() {
    Paper.init(this)
  }

}