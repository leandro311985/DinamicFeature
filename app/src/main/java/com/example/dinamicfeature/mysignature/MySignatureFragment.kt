package com.example.dinamicfeature.mysignature

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentSignatureBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MySignatureFragment : BaseFragment(R.layout.fragment_signature) {

  private lateinit var binding: FragmentSignatureBinding
  private val viewModel: MySignatureViewModel by viewModel()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }
  override fun initView(view: View) {
    setViewBinding(view)
    setLoading(true)
    setElement()
    lifecycleScope.launch {
      delay(2000)
      setLoading(false)
    }
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSignatureBinding.bind(view)
  }

  private fun setLoading(isVisible:Boolean){
    binding.loadingContainerSignature.isVisible = isVisible
  }

  private fun setElement() = binding.apply {
    btnHelp.setOnClickListener {
      viewModel.logout()
    }

  }
  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.logout.collect { result ->
            if (result) Toast.makeText(requireContext(), "desconctado", Toast.LENGTH_SHORT).show()
            activity?.finish()
          }
        }
      }
    }
  }

}