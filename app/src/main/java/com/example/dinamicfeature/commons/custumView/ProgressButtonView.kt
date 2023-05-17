package com.example.dinamicfeature.commons.custumView

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.example.dinamicfeature.R
import com.example.dinamicfeature.databinding.ProgressButtonViewBinding

class ProgressButtonView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  // Theme attribute for the default ProgressButtonView style. It will come in handy later.
  defStyleAttr: Int = R.attr.progressButtonViewDefaultStyle
) : FrameLayout(context, attrs, defStyleAttr) {

  private var buttonText: String?

  private val button: Button
  private val progressBar: ProgressBar

  init {
     val binding = ProgressButtonViewBinding.inflate(LayoutInflater.from(context))


    button = binding.button
    progressBar = binding.progressBar

    val attr = context.obtainStyledAttributes(
      attrs,
      R.styleable.ProgressButtonView,
      defStyleAttr,
      0
    )

    try {
      buttonText = attr.getString(R.styleable.ProgressButtonView_android_text)
      button.text = buttonText
      isEnabled = attr.getBoolean(R.styleable.ProgressButtonView_android_enabled, true)
    } finally {
      attr.recycle()
    }
  }

  override fun setOnClickListener(listener: OnClickListener?) {
    super.setOnClickListener(listener)
    button.setOnClickListener {
      listener?.onClick(it)
    }
  }

  override fun setEnabled(enabled: Boolean) {
    super.setEnabled(enabled)
    button.isEnabled = enabled
  }

  fun setButtonText(textRes: String) {
    setButtonTextIntern(textRes)
  }

  fun setButtonTextIntern(text: String?) {
    buttonText = text
    setTextVisibility(isVisible = !progressBar.isVisible)
  }

  fun setProgressButtonState(state: ProgressButtonState) {
    when (state) {
      ProgressButtonState.IDLE -> {
        progressBar.isVisible = false
        setTextVisibility(true)
        isEnabled = true
      }
      ProgressButtonState.IN_PROGRESS -> {
        progressBar.isVisible = true
        setTextVisibility(false)
        isEnabled = false
      }
      ProgressButtonState.DISABLED -> {
        progressBar.isVisible = false
        setTextVisibility(true)
        isEnabled = false
      }
    }
  }

  private fun setTextVisibility(isVisible: Boolean) {
    if (isVisible) {
      button.text = buttonText
    } else {
      button.text = ""
    }
  }
}

enum class ProgressButtonState {
  IDLE, IN_PROGRESS, DISABLED
}