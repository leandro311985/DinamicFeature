package com.example.dinamicfeature.presentation.journey.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentPainelAvatarBinding

class AvatarPainelFragment() : BaseFragment(R.layout.fragment_painel_avatar) {

  private lateinit var binding: FragmentPainelAvatarBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentPainelAvatarBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    avatar1.setOnClickListener { findNavController().navigate(AvatarPainelFragmentDirections.actionStep1ToNavigationStep2("1")) }
    avatar2.setOnClickListener { findNavController().navigate(AvatarPainelFragmentDirections.actionStep1ToNavigationStep2("2")) }
    avatar3.setOnClickListener { findNavController().navigate(AvatarPainelFragmentDirections.actionStep1ToNavigationStep2("3")) }
    avatar4.setOnClickListener { findNavController().navigate(AvatarPainelFragmentDirections.actionStep1ToNavigationStep2("4")) }
    avatar5.setOnClickListener { findNavController().navigate(AvatarPainelFragmentDirections.actionStep1ToNavigationStep2("5")) }
    avatar6.setOnClickListener { findNavController().navigate(AvatarPainelFragmentDirections.actionStep1ToNavigationStep2("6")) }

  }
}