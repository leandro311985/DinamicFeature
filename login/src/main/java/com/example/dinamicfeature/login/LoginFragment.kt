package com.example.dinamicfeature.login

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dinamicfeature.baseApp.common.BaseFragment
import com.example.dinamicfeature.login.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel



class LoginFragment : BaseFragment(R.layout.fragment_login) {

  private lateinit var binding: FragmentLoginBinding
  private val viewModel: LoginViewModel by viewModel()


  override fun initView(view: View) {
    setViewBinding(view)
    getText()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentLoginBinding.bind(view)
  }

  private fun getText(){
    viewModel.getUser()
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.user.collect { result ->
            binding.txt.text = result
          }
        }
      }
    }
  }

}