package com.example.dinamicfeature.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentLoginBinding
import com.example.extension.createAlertDialogOneButton
import com.example.extension.hideKeyboard
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment(R.layout.fragment_login) {

  private lateinit var binding: FragmentLoginBinding
  private val viewModel: LoginViewModel by viewModel()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }
  override fun initView(view: View) {
    setViewBinding(view)
    loading(true)
    checkIsLogged()
    getText()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentLoginBinding.bind(view)
  }

  private fun loading(isVisible:Boolean) = binding.apply{
    loadingContainer.isVisible = isVisible
  }

  private fun checkIsLogged(){
    viewModel.isLogged()
  }

  private fun getText() = binding.apply {
    forgotPasswordTextView.setOnClickListener { findNavController().navigate(R.id.login_fragment_to_navigation_create) }

    loginButton.setOnClickListener {
      loading(true)
      val userEmail = emailEditText.text.toString().trim()
      val password = passwordEditText.text.toString().trim()

      if (userEmail.isEmpty() || password.isEmpty()) {
        Toast.makeText(requireContext(), "Existe campos vazio", Toast.LENGTH_SHORT).show()
        loading(false)
      } else {
        viewModel.login(emailEditText.text.toString(), passwordEditText.text.toString())
        context?.hideKeyboard(it)
      }
    }

    loginSocialButton.setOnClickListener {
      showDialog()
    }

  }

  private fun showDialog() {
    requireContext().createAlertDialogOneButton(
      getString(com.example.dinamicfeature.R.string.app_name),
      getString(com.example.dinamicfeature.R.string.title_mysignature),
      getString(com.example.dinamicfeature.R.string.HOME_ADD_PRODUCT)
    ) {

    }
  }


  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.user.collect { result ->
            loading(false)
            if (result) findNavController().navigate(R.id.action_loginFragment_to_profile) else Toast.makeText(requireContext(), "Falido login", Toast.LENGTH_SHORT).show()
          }
        }

        launch {
          viewModel.isLogged.collect { result ->
            loading(false)
            if (result) findNavController().navigate(R.id.action_loginFragment_to_profile)

          }
        }
      }
    }
  }

}