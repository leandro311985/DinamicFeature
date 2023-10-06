package com.example.dinamicfeature.presentation.journey.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentChooseBinding

class ChooseFragment : BaseFragment(R.layout.fragment_choose) {

  private lateinit var binding: FragmentChooseBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentChooseBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    btnSave.setOnClickListener {
      findNavController().navigate(ChooseFragmentDirections.actionChooseFragmentToHome())
    }

  }

}