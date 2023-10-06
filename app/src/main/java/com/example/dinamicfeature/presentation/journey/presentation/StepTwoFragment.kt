package com.example.dinamicfeature.presentation.journey.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentStep2Binding

class StepTwoFragment : BaseFragment(R.layout.fragment_step2) {

  private lateinit var binding: FragmentStep2Binding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentStep2Binding.bind(view)
  }

  private fun setElements() = binding.apply {
    btnSave.setOnClickListener {
      findNavController().navigate(StepTwoFragmentDirections.actionStep5ToNavigationInit())
    }
  }
}