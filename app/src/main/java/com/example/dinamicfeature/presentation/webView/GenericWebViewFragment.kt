package com.example.dinamicfeature.presentation.webView

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentGenericWebViewBinding
import com.example.extension.setEllipsis


class GenericWebViewFragment : BaseFragment(R.layout.fragment_generic_web_view) {


  private lateinit var binding: FragmentGenericWebViewBinding
  private lateinit var genericWebViewViewObject: GenericWebViewViewObject

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    checkArgs()
    setEvents()
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentGenericWebViewBinding.bind(view)
  }

  private fun checkArgs() {
    genericWebViewViewObject = GenericWebViewViewObject()
    genericWebViewViewObject.title = "Campsite.bio"
  }

  private fun setElements() {
    binding.title.text = genericWebViewViewObject.title?.setEllipsis(25)

    binding.back.setOnClickListener {
      findNavController().navigateUp()
    }


    binding.genericWebView.apply {
      settings.javaScriptEnabled = true
      clearCache(true)
      settings.domStorageEnabled = true
    }
    binding.genericWebView.webViewClient = object : WebViewClient() {
      override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
      ) {
        super.onReceivedError(view, request, error)
        findNavController().navigateUp()
      }
    }

    binding.genericWebView.loadUrl(genericWebViewViewObject.url)

  }

  private fun setEvents() {

  }

}