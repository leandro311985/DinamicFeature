package com.example.dinamicfeature.createAccount

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.baseApp.common.BaseFragment
import com.example.dinamicfeature.baseApp.common.UiState
import com.example.dinamicfeature.baseApp.common.isValidEmail
import com.example.dinamicfeature.login.R
import com.example.dinamicfeature.login.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountFragment : BaseFragment(R.layout.fragment_register) {

  private lateinit var binding: FragmentRegisterBinding
  private val viewModel: CreateAccountViewModel by viewModel()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentRegisterBinding.bind(view)
    create()
    setCollectors()
  }

  private fun create() = binding.apply {
    registerButton.setOnClickListener {
      if (validation()) viewModel.createAccount(emailEditText.text.toString(), passwordEditText.text.toString(), nameEditText.text.toString())
    }

    backTextView.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun validation(): Boolean {
    var isValid = true

    if (binding.emailEditText.text.isNullOrEmpty()) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.enter_email), Toast.LENGTH_SHORT).show()

    } else {
      if (!binding.emailEditText.text.toString().isValidEmail()) {
        isValid = false
        Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.invalid_password), Toast.LENGTH_SHORT).show()

      }
    }
    if (binding.passwordEditText.text.isNullOrEmpty()) {
      isValid = false
      Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.enter_password), Toast.LENGTH_SHORT).show()

    } else {
      if (binding.passwordEditText.text.toString().length < 8) {
        isValid = false
        Toast.makeText(requireContext(), getString(com.example.dinamicfeature.R.string.password), Toast.LENGTH_SHORT).show()

      }
    }
    return isValid
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.register.collect { result ->
            when (result) {
              is UiState.Loading -> {

              }
              is UiState.Failure -> {

              }
              is UiState.Success -> {
                findNavController().navigate(R.id.action_loginFragment_to_home)
              }
            }
          }
        }
      }
    }
  }
}