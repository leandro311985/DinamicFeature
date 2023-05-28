package com.example.dinamicfeature.mysignature

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.mysignature.databinding.FragmentSignatureBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MySignatureFragment : BaseFragment(R.layout.fragment_signature) {

  private lateinit var binding: FragmentSignatureBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }
  override fun initView(view: View) {
    setViewBinding(view)
    setLoading(true)
    lifecycleScope.launch {
      delay(2000)
      setLoading(false)
    }
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSignatureBinding.bind(view)
  }

  private fun setLoading(isVisible:Boolean){
    binding.loadingContainerSignature.isVisible = isVisible
  }

}