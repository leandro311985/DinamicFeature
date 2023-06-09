package com.example.dinamicfeature.home

import android.view.View
import com.example.dinamicfeature.baseApp.common.BaseFragment
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home){

  private lateinit var binding: FragmentHomeBinding

  override fun initView(view: View) {
    setViewBinding(view)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentHomeBinding.bind(view)
  }
}