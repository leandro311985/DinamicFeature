package com.example.dinamicfeature.di.modules.repositories


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val moshiModule = module {
  single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
}
