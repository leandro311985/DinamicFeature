package com.example.extension

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.annotation.StringRes
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.runBlocking

/**
 * Extension method to show a keyboard for View.
 */
fun View.showKeyboard() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  this.requestFocus()
  imm.showSoftInput(this, 0)
}
/**
 * Try to hide the keyboard and returns whether it worked
 * Please note that this is a Context extension.
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun Context.hideKeyboard(view: View?): Boolean {
  try {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
  } catch (ignored: RuntimeException) { }
  return false
}

/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.show() : View {
  if (visibility != View.VISIBLE) {
    visibility = View.VISIBLE
  }
  return this
}


/**
 * Remove the view (visibility = View.GONE)
 */
fun View.hide() : View {
  if (visibility != View.GONE) {
    visibility = View.GONE
  }
  return this
}

fun View.getBitmap(): Bitmap {
  val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
  val canvas = Canvas(bmp)
  draw(canvas)
  canvas.save()
  return bmp
}

fun Button.setOnClickWithId(clickListener: (Int) -> Unit) {
  this.setOnClickListener {
    val buttonId = this.id
    clickListener(buttonId)
  }
}


