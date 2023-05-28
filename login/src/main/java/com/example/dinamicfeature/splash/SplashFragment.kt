package com.example.dinamicfeature.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.login.R
import com.example.dinamicfeature.login.databinding.FragmentLoginBinding
import com.example.dinamicfeature.login.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

  private lateinit var binding: FragmentSplashBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }
  override fun initView(view: View) {
    setViewBinding(view)
    delayPost()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSplashBinding.bind(view)
  }

  private fun delayPost(){
    lifecycleScope.launch {
      kotlinx.coroutines.delay(2500)
      findNavController().navigate(R.id.action_splash_to_loginFragment)
    }
  }

}