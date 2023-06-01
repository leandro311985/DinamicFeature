package com.example.dinamicfeature.searchpeople


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.searchpeople.databinding.FragmentSearchPeopleBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment(R.layout.fragment_search_people) {

  private lateinit var binding: FragmentSearchPeopleBinding
  private val viewModel: SearchViewModel by viewModel()
  private var userData: UserFirebase? = null

  override fun onResume() {
    super.onResume()
    viewModel.getPhoto()
    viewModel.getDataUser()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    binding.searchView.setOnClickListener {
      findNavController().navigate(R.id.action_search_fragment_to_search_fragment_list)
    }
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSearchPeopleBinding.bind(view)
  }


  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

        launch {
          viewModel.photoUser.collect { photo ->
            setElements(photo)
          }
        }
        launch {
          viewModel.userData.collect { dataUser ->
            userData = dataUser
           binding.containerUserr.name.text = userData?.first_name?.toUpperCase()
          }
        }
      }
    }
  }

  private fun setElements(photo: String) = binding.apply {
    if (photo.isEmpty()){
      Picasso.get()
        .load(com.example.dinamicfeature.R.drawable.ic_person)
        .into(containerUserr.logoImageView)
    }
    Picasso.get()
      .load(photo)
      .placeholder(com.example.dinamicfeature.R.drawable.ic_person)
      .into(containerUserr.logoImageView)

  }


}