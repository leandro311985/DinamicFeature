package com.example.dinamicfeature.login.splash

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentSplashBinding
import com.example.dinamicfeature.login.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

  private lateinit var binding: FragmentSplashBinding
  private val viewModel: SplashViewModel by viewModel()

  override fun onResume() {
    super.onResume()
    viewModel.isLogged()
  }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }
  override fun initView(view: View) {
    setViewBinding(view)
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSplashBinding.bind(view)
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.isLogged.collect { result ->
            kotlinx.coroutines.delay(3500)
            if (result) findNavController().navigate(R.id.action_splash_to_profile) else findNavController().navigate(R.id.action_splash_to_loginFragment)

          }
        }
      }
    }
  }
}