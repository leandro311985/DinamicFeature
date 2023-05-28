package com.example.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import com.example.baseApp.databinding.LayoutAlertDialogButtonBinding

private lateinit var alertDialogOneButton: AlertDialog


fun Context.createAlertDialogOneButton(
  title: String,
  message: String,
  titleButton: String,
  buttonListener: View.OnClickListener?
) {

  if (::alertDialogOneButton.isInitialized && alertDialogOneButton.isShowing)
    return

  alertDialogOneButton = AlertDialog.Builder(this).create()

  val binding = LayoutAlertDialogButtonBinding.inflate(LayoutInflater.from(this))

  alertDialogOneButton.apply {
    setView(binding.root)
    setCancelable(true)
  }

  binding.dialogTitleTextView.text = title.fromHtml()
  binding.dialogMessageTextView.text = message.fromHtml()
  binding.dialogTextButton.text = titleButton

  buttonListener?.let {
    binding.dialogTextButton.setOnClickListener {
      buttonListener.onClick(it)
      alertDialogOneButton.dismiss()
    }
  } ?: run {
    binding.dialogTextButton.setOnClickListener {
      alertDialogOneButton.dismiss()
    }
  }

  alertDialogOneButton.show()
}

fun String.fromHtml(): String {
  return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}