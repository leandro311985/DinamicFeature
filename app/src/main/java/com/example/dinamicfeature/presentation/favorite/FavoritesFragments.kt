package com.example.dinamicfeature.presentation.favorite

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
import com.example.dinamicfeature.databinding.FragmentFavoritesBinding
import com.example.dinamicfeature.domain.models.PersonsFakeHome
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragments : BaseFragment(R.layout.fragment_favorites) {

  private lateinit var binding: FragmentFavoritesBinding
  private val viewModel: FavoritesViewModel by viewModel()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    loading(true)
    setElements()
    getList()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentFavoritesBinding.bind(view)
  }

  private fun getList() {
    viewModel.getList()
  }

  private fun setElements() = binding.apply{
    containerToolbar.title.text = "Favoritos"
    containerToolbar.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun loading(isVisible: Boolean) = binding.apply {
    loadingContainer.loadingContainer.isVisible = isVisible
    containerToolbar.backgraund.isVisible = !isVisible
    containerMacths.isVisible = !isVisible
    containerLike.isVisible = !isVisible
    containerLinearLine.isVisible = !isVisible
    containerLinearLike.isVisible = !isVisible
    containerLinearMessage.isVisible = !isVisible
    containerPerhaps.isVisible = !isVisible
    containerLinearMyLikes.isVisible = !isVisible
  }

  private fun upDateList(list: List<PersonsFakeHome>) {
    binding.apply {
      val rc = binding.rcFavorites
      rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapter = FavoritesAdapter(requireContext(),list)
      rc.adapter = adapter

      val rcMessage = binding.rcMessage
      rcMessage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      val adapterMessage = FavoritesMenssagesAdapter(requireContext(),list)
      rcMessage.adapter = adapterMessage

      val rcLike = binding.rcLike
      rcLike.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterLike = FavoritesAdapter(requireContext(),list)
      rcLike.adapter = adapterLike

      val rcMyLike = binding.rcMyLikes
      rcMyLike.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterMyLike = FavoritesAdapter(requireContext(),list)
      rcMyLike.adapter = adapterMyLike

      val rcPerhaps = binding.rcPerhaps
      rcPerhaps.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterPerhaps = FavoritesAdapter(requireContext(),list)
      rcPerhaps.adapter = adapterPerhaps
    }
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.listHome.collect { result ->
            upDateList(result)
            loading(false)
          }
        }
      }
    }
  }
}