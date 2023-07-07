package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

  private lateinit var binding: FragmentRegisterBinding

  private val viewModel: RegisterViewModel by sharedViewModel()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    viewModel.getRegisterUser()
    viewModel.getRegisterUserPhysicalData()
    viewModel.getRegisterUserGeneralData()
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentRegisterBinding.bind(view)

  }

  private fun setElements() = binding.apply {
    toolbarContainerRegister.img.setOnClickListener {
      findNavController().navigateUp()
    }
    imageView1.setOnClickListener {
      findNavController().navigate(RegisterFragmentDirections.actionRegisterToNavigationBasic())
    }
    imageView2.setOnClickListener {
      findNavController().navigate(RegisterFragmentDirections.actionRegisterToProfileFisico())
    }
    imageView3.setOnClickListener {
      findNavController().navigate(RegisterFragmentDirections.actionRegisterToProfileGeral())
    }
    imageView4.setOnClickListener {
      findNavController().navigate(RegisterFragmentDirections.actionRegisterToProfileGeral())
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.getDataRegister.collect { result ->
            when (result?.countFilledFields) {
              1 -> binding.containerBarra.barra1.isVisible = true
              2 -> {
                binding.containerBarra.barra2.isVisible = true
                binding.containerBarra.barra1.isVisible = true
              }

              3 -> {
                binding.containerBarra.barra1.isVisible = true
                binding.containerBarra.barra2.isVisible = true
                binding.containerBarra.barra3.isVisible = true
              }

              4 -> {
                binding.containerBarra.barra4.isVisible = true
                binding.containerBarra.barra1.isVisible = true
                binding.containerBarra.barra2.isVisible = true
                binding.containerBarra.barra3.isVisible = true
              }

            }

          }
        }

        launch {
          viewModel.getDataPhysicalData.collect { result ->
            when (result?.countFilledFields) {
              1 -> binding.containerBarra2.barra1.isVisible = true
              2 -> {
                binding.containerBarra2.barra2.isVisible = true
                binding.containerBarra2.barra1.isVisible = true
              }

              3 -> {
                binding.containerBarra2.barra1.isVisible = true
                binding.containerBarra2.barra2.isVisible = true
                binding.containerBarra2.barra3.isVisible = true
              }

              4 -> {
                binding.containerBarra2.barra4.isVisible = true
                binding.containerBarra2.barra1.isVisible = true
                binding.containerBarra2.barra2.isVisible = true
                binding.containerBarra2.barra3.isVisible = true
              }

            }

          }
        }
        launch {
          viewModel.getDataProfileGeneralData.collect { result ->
            when (result?.countFilledFields) {
              1 -> binding.containerBarra3.barra1.isVisible = true
              2 -> {
                binding.containerBarra3.barra2.isVisible = true
                binding.containerBarra3.barra1.isVisible = true
              }

              3 -> {
                binding.containerBarra3.barra1.isVisible = true
                binding.containerBarra3.barra2.isVisible = true
                binding.containerBarra3.barra3.isVisible = true
              }

              4 -> {
                binding.containerBarra3.barra4.isVisible = true
                binding.containerBarra3.barra1.isVisible = true
                binding.containerBarra3.barra2.isVisible = true
                binding.containerBarra3.barra3.isVisible = true
              }
            }

          }
        }
      }
    }
  }

}