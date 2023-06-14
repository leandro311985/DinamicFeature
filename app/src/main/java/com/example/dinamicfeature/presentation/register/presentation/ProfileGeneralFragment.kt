package com.example.dinamicfeature.presentation.register.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentGeralProfileBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileGeneralFragment : BaseFragment(R.layout.fragment_geral_profile) {

  private lateinit var binding: FragmentGeralProfileBinding
  private val viewModel: RegisterViewModel by sharedViewModel()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentGeralProfileBinding.bind(view)

  }

  private fun setElements() {
    binding.back.setOnClickListener {
      findNavController().navigateUp()
    }
  }
}