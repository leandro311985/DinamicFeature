package com.example.dinamicfeature.presentation.shop

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerShopAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> HealthFragment()
      1 -> DoctorFragment()
      2 -> MyShop()
      3 -> TripFragment()
      4 -> BossFragment()
      else -> throw IllegalArgumentException("Invalid position: $position")
    }
  }

  override fun getCount(): Int {
    return 5 // número total de telas
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return when (position) {
      0 -> "Saúde Mental"
      1 -> "Doutor Potilid"
      2 -> "Meu\n shopping"
      3 -> "Viaje bem"
      4 -> "Chefe Potlid"
      else -> null
    }
  }
}







