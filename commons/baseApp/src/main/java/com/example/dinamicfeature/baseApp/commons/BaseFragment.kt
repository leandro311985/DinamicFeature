package com.example.dinamicfeature.baseApp.commons

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes resId: Int) : Fragment(resId) {

  protected abstract fun initView(view: View)
  protected abstract fun setViewBinding(view: View)

  protected val onBackPressedCallbackToClose = object : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
      requireActivity().finish()
    }
  }

}