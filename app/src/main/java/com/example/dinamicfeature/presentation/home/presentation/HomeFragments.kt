package com.example.dinamicfeature.presentation.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentHomeBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.dinamicfeature.presentation.login.presentation.LoginViewModel
import com.example.dinamicfeature.presentation.searchpeople.adapter.PeopleCardAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragments : BaseFragment(R.layout.fragment_home) {

  private lateinit var binding: FragmentHomeBinding
  private val viewModel: HomeViewModel by viewModel()



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    loading(true)
    setElements()
    getList(TypeVisual.GRADE)
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentHomeBinding.bind(view)
  }

  private fun setElements() = binding.apply{
    containerToolbar.title.text = "Home"
    containerToolbar.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
    containerTimeLine.iconeMenu4.setOnClickListener {
      getList(TypeVisual.GRADE)
    }
    containerTimeLine.iconeVisial.setOnClickListener {
      getList(TypeVisual.NORMAL)
    }
  }

  private fun loading(isVisible: Boolean) = binding.apply {
    loadingContainerHome.loadingContainer.isVisible = isVisible
    containerToolbar.backgraund.isVisible = !isVisible
    containerTimeLine.containerLineTime.isVisible = !isVisible
    rcHome.isVisible = !isVisible
  }


  private fun getList(typeVisual: TypeVisual){
    viewModel.getList(typeVisual)
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.listHomeGrade.collect { result ->
            binding.rcHome.isVisible = true
            binding.rcHomeNormal.isVisible = false
            upDateList(result)
            loading(false)
          }
        }

        launch {
          viewModel.listHomeNormal.collect { result ->
            binding.rcHome.isVisible = false
            binding.rcHomeNormal.isVisible = true
            upDateListNormal(result)
            loading(false)
          }
        }
      }
    }
  }

  private fun upDateList(emmanuelleModeViewObject: List<PersonsFake>) {
    binding.apply {
      val gridView = binding.rcHome
      val adapter = ListHomeAdapter(requireContext(), emmanuelleModeViewObject)
      gridView.adapter = adapter
    }
  }

  private fun upDateListNormal(personsFake: List<PersonsFake>) {
    binding.apply {
      val gridView = binding.rcHomeNormal
      gridView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      val adapter = ListHomeNormalAdapter(requireContext(), personsFake)
      gridView.adapter = adapter
    }
  }
}