package com.example.dinamicfeature.presentation.shop

import android.os.Bundle
import android.view.View
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentMyShopBinding

class MyShop : BaseFragment(R.layout.fragment_my_shop) {

  private lateinit var binding: FragmentMyShopBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentMyShopBinding.bind(view)
  }

}