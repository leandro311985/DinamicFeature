package com.example.extension

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.baseApp.R
import com.google.android.material.snackbar.Snackbar

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


fun Context.createSnackbar(
  parent: View,
  message: String,
  duration: Int,
  titleAction: String? = null,
  listener: View.OnClickListener? = null
) {

  val snackbar = Snackbar.make(parent, message, duration)
  val snackbarView = snackbar.view

  snackbar.setText(message.fromHtml())
  snackbar.setActionTextColor(Color.parseColor("#FFFFFF"))
  titleAction?.let {
    snackbar.setAction(titleAction, listener ?: View.OnClickListener { })
  }

  snackbarView.setBackgroundColor(Color.parseColor("#A2DAFD"))
  val snackTextView =
    snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
  snackTextView.maxLines = 4

  snackbar.show()
}