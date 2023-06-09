package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentBasicDataBinding
import com.example.dinamicfeature.databinding.FragmentRegisterBinding

class BasicDataFragment : BaseFragment(R.layout.fragment_basic_data) {

  private lateinit var binding: FragmentBasicDataBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    binding.back.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  override fun setViewBinding(view: View) {
    binding = FragmentBasicDataBinding.bind(view)

  }
}