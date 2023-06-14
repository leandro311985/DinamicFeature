package com.example.dinamicfeature.presentation.login.presentation.logout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentLogoutBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutFragment : BaseFragment(R.layout.fragment_logout) {

  private lateinit var binding: FragmentLogoutBinding
  private val viewModel: LogoutViewModel by viewModel()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    loading(true)
    setCollectors()
    logout()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentLogoutBinding.bind(view)

  }

  private fun logout() {
    lifecycleScope.launch {
      delay(2000)
      viewModel.logout()
    }
  }

  private fun loading(isVisible: Boolean) = binding.apply {
    loadingContainer.loadingContainer.isVisible = isVisible
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.logout.collect { result ->
            if (result) Toast.makeText(requireContext(), "desconectado", Toast.LENGTH_SHORT).show()
            activity?.finish()
          }
        }
      }
    }
  }
}