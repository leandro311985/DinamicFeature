package com.example.dinamicfeature.presentation.journey.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentChooseTestBinding

class ChooseTestFragment  : BaseFragment(R.layout.fragment_choose_test) {

  private lateinit var binding: FragmentChooseTestBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentChooseTestBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    btn1.setOnClickListener {
      findNavController().navigate(ChooseTestFragmentDirections.actionNavigationChooseTestToChoose())
    }

    btn2.setOnClickListener {
      findNavController().navigate(ChooseTestFragmentDirections.actionNavigationChooseTestToChoose())
    }
  }


}