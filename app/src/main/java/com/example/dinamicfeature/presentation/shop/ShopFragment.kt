package com.example.dinamicfeature.presentation.shop

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentShopBinding

class ShopFragment : BaseFragment(R.layout.fragment_shop) {

  private lateinit var binding: FragmentShopBinding

  private lateinit var viewPager: ViewPager

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
    setViewPage()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentShopBinding.bind(view)
  }

  private fun setElements() = binding.apply{
    containerToolbarShop.icToolbarMenu.isVisible = true
    containerToolbarShop.icToolbarMenu.setOnClickListener {
      findNavController().navigate(ShopFragmentDirections.actionNavigationShopFragmentToConfigFragment())
    }
    containerToolbarShop.title.text = "Shopping Potlid"
    containerToolbarShop.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun setViewPage() = binding.apply{
    this@ShopFragment.viewPager = viewPager
    val adapter = fragmentManager?.let { ViewPagerShopAdapter(it) }
    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)
    viewPager.currentItem = 2
  }
}