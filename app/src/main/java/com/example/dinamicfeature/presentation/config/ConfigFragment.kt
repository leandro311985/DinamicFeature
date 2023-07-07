package com.example.dinamicfeature.presentation.config

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentConfigBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigFragment : BaseFragment(R.layout.fragment_config), ConfigListener {

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

  private fun setElements() = binding.apply {
    containerToolbarConfig.title.text = "Configuração"
    containerToolbarConfig.backgraund.setBackgroundColor(resources.getColor(R.color.DEFAULT_GRAY_APP))
    containerToolbarConfig.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
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
      val adapter = ConfigAdapter(viewObject, this@ConfigFragment)
      listView.adapter = adapter
    }
  }

  override fun onItemClick(configData: ConfigData) {
    when (configData.id) {
      "1" -> findNavController().navigate(ConfigFragmentDirections.actionConfigFragmentToSearchSetting())
      "2" -> {}
      "3" -> findNavController().navigate(ConfigFragmentDirections.actionConfigFragmentToRegister())
      "4" -> findNavController().navigate(ConfigFragmentDirections.actionConfigFragmentToMyAssinature())
      "5" -> {}
      "6" -> {}
      "7" -> {}
      "8" -> {}
      "9" -> {}
      "10" -> {}
      "11" -> {}
      "12" -> findNavController().navigate(ConfigFragmentDirections.actionConfigFragmentToAvatar())
      "13" -> {}
      "14" -> {}
      "15" -> {}
      "16" -> {}
      "17" -> {}
    }
  }
}