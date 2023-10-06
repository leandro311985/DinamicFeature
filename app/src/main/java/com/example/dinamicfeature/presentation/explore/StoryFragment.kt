package com.example.dinamicfeature.presentation.explore

import android.os.Bundle
import android.view.View
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentStoryBinding

class StoryFragment : BaseFragment(R.layout.fragment_story) {

  private lateinit var binding: FragmentStoryBinding
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentStoryBinding.bind(view)
  }

  private fun setElements() = binding.apply {

  }
}