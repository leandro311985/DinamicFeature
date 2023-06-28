package com.example.dinamicfeature.presentation.config

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentConfigBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigFragment : BaseFragment(R.layout.fragment_config),ConfigListener {

  private lateinit var binding: FragmentConfigBinding
  private val viewModel: ConfigViewModel by viewModel()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentConfigBinding.bind(view)
  }

  private fun setElements() = binding.apply{
    containerToolbarConfig.title.text = "Configuração"
    containerToolbarConfig.backgraund.setBackgroundColor(resources.getColor(R.color.DEFAULT_GRAY_APP))
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.listFlow.collect { result ->
            upDateList(result)
          }
        }
      }
    }
  }

  private fun upDateList(viewObject: List<ConfigData>) {
    binding.apply {
      val listView = binding.rcConfig
      listView.layoutManager = LinearLayoutManager(requireContext())
      val adapter = ConfigAdapter(viewObject)
      listView.adapter = adapter
    }
  }

  override fun onItemClick(configData: ConfigData) {
    TODO("Not yet implemented")
  }
}