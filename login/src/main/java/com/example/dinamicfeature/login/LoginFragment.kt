package com.example.dinamicfeature.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.baseApp.common.BaseFragment
import com.example.dinamicfeature.commons.custumView.ProgressButtonState
import com.example.dinamicfeature.login.databinding.FragmentLoginBinding
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
    getText()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentLoginBinding.bind(view)
  }

  private fun getText() = binding.apply {
    forgotPasswordTextView.setOnClickListener { Toast.makeText(requireContext(), "botao cadstrar", Toast.LENGTH_SHORT).show() }

    loginButton.setOnClickListener {

      val userEmail = emailEditText.text.toString().trim()
      val password = passwordEditText.text.toString().trim()

      if (userEmail.isEmpty() || password.isEmpty()) {
        Toast.makeText(requireContext(), "Existe campos vazio", Toast.LENGTH_SHORT).show()
      } else {
        viewModel.login(emailEditText.text.toString(), passwordEditText.text.toString())
      }
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.user.collect { result ->
            if (result) findNavController().navigate(R.id.action_loginFragment_to_home) else Toast.makeText(requireContext(), "Falido login", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }

}