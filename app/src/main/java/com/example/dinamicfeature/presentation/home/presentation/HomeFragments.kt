package com.example.dinamicfeature.presentation.home.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentHomeBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.ProfileGeneralData
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragments : BaseFragment(R.layout.fragment_home), HomeListener, HomeViewPagerListener {

  private lateinit var binding: FragmentHomeBinding
  private val viewModel: HomeViewModel by viewModel()
  private var data = ProfileGeneralData()
  private lateinit var adapterViewPager: PhotoPagerAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    getData()
    loading(true)
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentHomeBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    containerToolbar.icToolbarMenu.isVisible = true
    containerToolbar.icToolbarMenu.setOnClickListener {
      findNavController().navigate(HomeFragmentsDirections.actionNavigationHomeFragmentToConfigFragment())
    }
    containerToolbar.title.text = "Home"
    containerToolbar.imgBack.isVisible = false
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
  }

  private fun getData() {
    viewModel.getRegisterUserGeneralData()
  }


  private fun getList(typeVisual: TypeVisual) {
    viewModel.getList(typeVisual)
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.getDataProfileGeneralData.collect { dataResult ->
            if (dataResult != null) {
              data = dataResult
              getList(TypeVisual.NORMAL)
            } else {
              getList(TypeVisual.NORMAL)

            }
          }
        }
        launch {
          viewModel.listHomeGrade.collect { result ->
            binding.rcHome.isVisible = true
            binding.viewPager.isVisible = false
            upDateList(result as MutableList<PersonsFake>)
            loading(false)
          }
        }

        launch {
          viewModel.listHomeNormal.collect { result ->
            binding.rcHome.isVisible = false
            binding.viewPager.isVisible = true
            upDateListNormal(result as MutableList<PersonsFake>)
            loading(false)
          }
        }
      }
    }
  }

  private fun upDateList(emmanuelleModeViewObject: List<PersonsFake>) {
    binding.apply {
      val gridView = binding.rcHome
      val adapter = ListHomeAdapter(this@HomeFragments, requireContext(), emmanuelleModeViewObject)
      gridView.adapter = adapter
    }
  }

  private fun upDateListNormal(personsFake: List<PersonsFake>) {
    binding.viewPager.apply {
      adapterViewPager = PhotoPagerAdapter(this@HomeFragments, requireContext(), personsFake)
      binding.viewPager.adapter = adapterViewPager

    }
  }

  override fun onItemClick(personsFake: PersonsFake, positionInt: Int) {
    findNavController().navigate(HomeFragmentsDirections.actionHomeFragmentListToFragmentDetail(positionInt))
  }

  override fun onItemClickListenerViewPager(position: Int) {
    findNavController().navigate(HomeFragmentsDirections.actionHomeFragmentListToFragmentDetail(position))
  }
}

