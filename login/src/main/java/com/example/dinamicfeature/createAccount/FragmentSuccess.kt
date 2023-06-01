package com.example.dinamicfeature.createAccount

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.login.R
import com.example.dinamicfeature.login.databinding.FragmentSuccessBinding

class FragmentSuccess : BaseFragment(R.layout.fragment_success) {

  private lateinit var binding: FragmentSuccessBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElement()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSuccessBinding.bind(view)
  }

  private fun setElement() {
    binding.textOk.setOnClickListener {
      findNavController().navigate(R.id.action_success_to_profile)
    }
  }
}