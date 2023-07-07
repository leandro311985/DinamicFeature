package com.example.dinamicfeature.presentation.shop

import android.os.Bundle
import android.view.View
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentDoctorBinding

class DoctorFragment : BaseFragment(R.layout.fragment_doctor) {

  private lateinit var binding: FragmentDoctorBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentDoctorBinding.bind(view)
  }

}