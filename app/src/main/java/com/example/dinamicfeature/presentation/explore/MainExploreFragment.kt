package com.example.dinamicfeature.presentation.explore

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentMainExploreBinding
import com.example.dinamicfeature.presentation.shop.ShopFragmentDirections

class MainExploreFragment : BaseFragment(R.layout.fragment_main_explore) {

  private lateinit var binding: FragmentMainExploreBinding

  private lateinit var viewPager: ViewPager

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun onResume() {
    super.onResume()
    setViewPage()
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
    setViewPage()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentMainExploreBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    containerToolbarExplore.icToolbarMenu.isVisible = true
    containerToolbarExplore.icToolbarMenu.setOnClickListener {
      findNavController().navigate(ShopFragmentDirections.actionNavigationShopFragmentToConfigFragment())
    }
    containerToolbarExplore.title.text = "Explorar"
    containerToolbarExplore.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun setViewPage() = binding.apply {
    this@MainExploreFragment.viewPager = viewPager
    val adapter = fragmentManager?.let { ViewPagerExplorerAdapter(it) }
    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)
    viewPager.currentItem = 0
  }

}