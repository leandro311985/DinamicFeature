package com.example.dinamicfeature.register.presentation

import android.os.Bundle
import android.view.View
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

  }

  override fun setViewBinding(view: View) {
    binding = FragmentJourneyBinding.bind(view)

  }
}