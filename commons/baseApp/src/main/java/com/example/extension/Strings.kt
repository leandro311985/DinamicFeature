package com.example.extension

import android.content.Context

fun Context.getStringByName(stringName: String): String {
  return try {
    this.getString(this.resources.getIdentifier(stringName, "string", this.packageName))
  } catch (throwable: Throwable) {
    ""
  }
}

fun Context.getDrawableByName(stringName: String): Int {
  return this.resources.getIdentifier(stringName, "drawable", this.packageName);
}