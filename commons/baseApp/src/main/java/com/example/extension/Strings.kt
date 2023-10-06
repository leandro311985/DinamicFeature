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

fun String.setEllipsis(maxSizeText: Int): String {

  var finalText = this
  if(this.length > maxSizeText)
    finalText = "${this.substring(0, maxSizeText)}..."

  return finalText
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

  snackbarView.setBackgroundColor(Color.parseColor("#5A93E1"))
  val snackTextView =
    snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
  snackTextView.maxLines = 4

  snackbar.show()
}

fun countFilledFields(field1: String?, field2: String?, field3: String?, field4: String?,field5: String?): Int {
  var count = 0

  if (field1?.isNotEmpty() == true) {
    count++
  }

  if (field2?.isNotEmpty() == true) {
    count++
  }

  if (field3?.isNotEmpty() == true) {
    count++
  }

  if (field4?.isNotEmpty() == true) {
    count++
  }

  if (field5?.isNotEmpty() == true) {
    count++
  }

  return count
}
