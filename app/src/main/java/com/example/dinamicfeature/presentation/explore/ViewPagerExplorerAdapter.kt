package com.example.dinamicfeature.presentation.explore

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerExplorerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> ExploreFragment()
      1 -> StoryFragment()
      else -> throw IllegalArgumentException("Invalid position: $position")
    }
  }

  override fun getCount(): Int {
    return 2 // número total de telas
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return when (position) {
      0 -> "Buscar"
      1 -> "Story"
      else -> null
    }
  }
}