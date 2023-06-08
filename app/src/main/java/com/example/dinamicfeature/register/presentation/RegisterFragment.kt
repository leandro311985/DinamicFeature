package com.example.dinamicfeature.register.presentation

import android.os.Bundle
import android.view.View
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

  private lateinit var binding: FragmentRegisterBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)

  }

  override fun setViewBinding(view: View) {
    binding = FragmentRegisterBinding.bind(view)

  }
}