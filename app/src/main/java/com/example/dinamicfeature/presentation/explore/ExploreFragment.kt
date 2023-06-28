package com.example.dinamicfeature.presentation.explore

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentExploreBinding
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import com.example.dinamicfeature.presentation.favorite.FavoritesAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : BaseFragment(R.layout.fragment_explore) {

  private lateinit var binding: FragmentExploreBinding
  private val viewModel: ExploreViewModel by viewModel()



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    loading(true)
    getList()
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentExploreBinding.bind(view)
  }

  private fun getList(){
    viewModel.getList()
  }

  private fun loading(isVisible: Boolean) = binding.apply {
    loadingContaineExplorerr.loadingContainer.isVisible = isVisible
    containerToolbarExplore.backgraund.isVisible = !isVisible
    containerMacths.isVisible = !isVisible
    containerMensages.isVisible = !isVisible
    rcLive.isVisible = !isVisible
    nameExplorer.isVisible = !isVisible
    banner3.isVisible = !isVisible
    localizationExplorer.isVisible = !isVisible
    iconeLocation.isVisible = !isVisible
    localExplorer.isVisible = !isVisible
    containerLinearLike.isVisible = !isVisible
    containerLinearPerhaps.isVisible = !isVisible
    containerLinearMessage.isVisible = !isVisible
    destaque.isVisible = !isVisible
    destaquePerfil.isVisible = !isVisible
  }

  private fun setElements() = binding.apply{
    containerToolbarExplore.icToolbarMenu.isVisible= true
    containerToolbarExplore.icToolbarMenu.setOnClickListener {
      findNavController().navigate(ExploreFragmentDirections.actionNavigationExplorerFragmentToConfigFragment())
    }
    containerToolbarExplore.title.text = "Explorar"
    containerToolbarExplore.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.listHome.collect { result ->
            upDateList(result)
          }
        }
      }
    }
  }

  private fun upDateList(personsFakeHome: List<PersonsFakeHome>) {
    binding.apply {
      val rcList = binding.rcExplore
      rcList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterExplorer = FavoritesAdapter(requireContext(),personsFakeHome)
      rcList.adapter = adapterExplorer

      val rc = binding.rcAproximado
      rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapter = FavoritesAdapter(requireContext(),personsFakeHome)
      rc.adapter = adapter

      val rcLike = binding.rcSerio
      rcLike.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterLike = FavoritesAdapter(requireContext(),personsFakeHome)
      rcLike.adapter = adapterLike

      val rcMyLike = binding.rcDestaque
      rcMyLike.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterMyLike = FavoritesAdapter(requireContext(),personsFakeHome)
      rcMyLike.adapter = adapterMyLike

      val rcPerhaps = binding.rcLive
      rcPerhaps.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterPerhaps = FavoritesAdapter(requireContext(),personsFakeHome)
      rcPerhaps.adapter = adapterPerhaps

      loading(false)

    }
  }
}