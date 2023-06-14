package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentRegisterBinding
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
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentRegisterBinding.bind(view)

  }

  private fun setElements() = binding.apply{
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
}