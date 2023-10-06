package com.example.dinamicfeature.presentation.journey.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentStep1Binding

class StepOneFragment : BaseFragment(R.layout.fragment_step1) {

  private lateinit var binding: FragmentStep1Binding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentStep1Binding.bind(view)
  }

  private fun setElements() = binding.apply {
    btnSave.setOnClickListener {
      findNavController().navigate(StepOneFragmentDirections.actionStep4ToNavigationStep5())
    }
  }
}