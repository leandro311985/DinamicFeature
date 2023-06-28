package com.example.dinamicfeature.presentation.shop

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentShopBinding

class ShopFragment : BaseFragment(R.layout.fragment_shop) {

  private lateinit var binding: FragmentShopBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentShopBinding.bind(view)
  }

  private fun setElements() = binding.apply{
    containerToolbarShop.title.text = "Shopping Potlid"
    containerToolbarShop.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
  }
}