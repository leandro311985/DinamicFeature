package com.example.dinamicfeature.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

  private fun getText() {

    binding.forgotPasswordTextView.setOnClickListener(object : View.OnClickListener {
      override fun onClick(v: View?) {
        Toast.makeText(requireContext(), "botao cadstrar", Toast.LENGTH_SHORT).show()

      }

    })

    binding.toogle.setOnCheckedChangeListener { _, isChecked ->
      if (isChecked) {
        Toast.makeText(requireContext(), "botao ativado", Toast.LENGTH_SHORT).show()
      } else {
        // The toggle is disabled
      }
    }

    binding.loginButton.setOnClickListener {
      Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()

      val userEmail = binding.emailEditText.text.toString().trim()
      val password = binding.passwordEditText.text.toString().trim()

      if (userEmail.isEmpty() || password.isEmpty()) {
        Toast.makeText(requireContext(), "Existe campos vazio", Toast.LENGTH_SHORT).show()
      } else {
        viewModel.login(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
      }
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.user.collect { result ->
            Toast.makeText(requireContext(), "login com $result", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }

}