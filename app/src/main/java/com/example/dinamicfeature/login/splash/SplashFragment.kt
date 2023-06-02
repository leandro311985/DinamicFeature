package com.example.dinamicfeature.login.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

  private lateinit var binding: FragmentSplashBinding

  override fun onResume() {
    super.onResume()
    delayPost()
  }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }
  override fun initView(view: View) {
    setViewBinding(view)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSplashBinding.bind(view)
  }

  private fun delayPost(){
    lifecycleScope.launch {
      kotlinx.coroutines.delay(3500)
      findNavController().navigate(R.id.action_splash_to_loginFragment)
    }
  }

}