package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentJourneyBinding

class InitJourneyFragment : BaseFragment(R.layout.fragment_journey) {

  private lateinit var binding: FragmentJourneyBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentJourneyBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    btnCancel.setOnClickListener {
      findNavController().navigate(InitJourneyFragmentDirections.actionInitFragmentToProfile())

    }

    btnSave.setOnClickListener {
      findNavController().navigate(InitJourneyFragmentDirections.actionInitFragmentToRegisterFragment())

    }
  }
}