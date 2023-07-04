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
import com.example.dinamicfeature.domain.models.MyPersonsFake
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
    viewModel.getMyList()
    viewModel.getList()
  }

  private fun setElements() = binding.apply {
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

  private fun myMatchs(listMatchs: MutableList<MyPersonsFake>) = binding.apply {
    val rc = binding.rcMyLikes
    rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    val adapter = FavoritesMacthAdapter(requireContext(), listMatchs)
    rc.adapter = adapter
  }

  private fun myMatchsTalvez(listTalvez: MutableList<MyPersonsFake>) = binding.apply {
    val rcPerhaps = binding.rcPerhaps
    rcPerhaps.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    val adapterPerhaps = FavoritesMacthAdapter(requireContext(), listTalvez)
    rcPerhaps.adapter = adapterPerhaps
  }

  private fun upDateList(list: List<PersonsFakeHome>) {
    binding.apply {
      val rcMessage = binding.rcMessage
      rcMessage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      val adapterMessage = FavoritesMenssagesAdapter(requireContext(), list)
      rcMessage.adapter = adapterMessage

      val rcLike = binding.rcLike
      rcLike.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterLike = FavoritesAdapter(requireContext(),list)
      rcLike.adapter = adapterLike

      val rcMyLike = binding.rcFavorites
      rcMyLike.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val adapterMyLike = FavoritesAdapter(requireContext(), list)
      rcMyLike.adapter = adapterMyLike

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

        launch {
          viewModel.myListHome.collect { result ->
            myMatchs(result)
            loading(false)
          }
        }
        launch {
          viewModel.myListHomeTalvez.collect { result ->
            myMatchsTalvez(result)
            loading(false)
          }
        }
      }
    }
  }
}