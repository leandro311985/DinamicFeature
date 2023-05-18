package com.example.dinamicfeature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.baseApp.common.BaseFragment
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home){

  private lateinit var binding: FragmentHomeBinding
  private val viewModel: HomeViewModel by viewModel()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    teste()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentHomeBinding.bind(view)
  }

  private fun teste(){
    binding.btnHome.setOnClickListener {
      viewModel.logout()
    }
  }
  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.logout.collect { result ->
             Toast.makeText(requireContext(), "desconctado", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }
}