package com.example.profile

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.domain.models.Profile
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.profile.adapter.ProfileAdapter
import com.example.profile.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

  private lateinit var binding: FragmentProfileBinding
  private lateinit var profileAdapter: ProfileAdapter
  private val viewModel: ProfileViewModel by viewModel()
  private var auth: FirebaseAuth? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setLoading(true)
    auth = FirebaseAuth.getInstance()
    getDataUser()
    setCollectors()

  }

  override fun setViewBinding(view: View) {
    binding = FragmentProfileBinding.bind(view)
  }

  private fun getDataUser() {
    viewModel.getDataUser()
  }

  private fun setLoading(isVisible: Boolean) = binding.apply {
    loadingContainerProfile.isVisible = isVisible
  }

  private fun setElements(photo: String) = binding.apply {

    profileToolbar.toolbarTitle.text = "Meu Perfil"
    if (photo.isEmpty()) {
      return@apply
    } else {
      Picasso.get()
        .load(photo)
        .placeholder(com.example.dinamicfeature.R.drawable.ic_person)
        .into(ImageProfile)
    }
  }


  private fun init(user: UserFirebase) {
    val profiles = listOf(
      Profile(ContextCompat.getDrawable(requireContext(), com.example.dinamicfeature.R.drawable.ic_user_bottom),"Carla Marine","14 de Maio","Conversou pelo chat as 19:00"),
      Profile(ContextCompat.getDrawable(requireContext(), com.example.dinamicfeature.R.drawable.ic_user_bottom),"Maria das graças","27 de Maio","Acabou de adicionar voçê a lista de favoritos"),
      Profile(ContextCompat.getDrawable(requireContext(), com.example.dinamicfeature.R.drawable.ic_user_bottom),"Maria das graças","29 de Maio","Acabou de adicionar voçê a lista de favoritos"),
    )
    binding.rcTimeLine.layoutManager = LinearLayoutManager(requireContext())

    profileAdapter = ProfileAdapter(profiles)
    binding.rcTimeLine.adapter = profileAdapter
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.user.collect { user ->
            viewModel.getPhoto()
            init(user)
            setLoading(false)
          }
        }
        launch {
          viewModel.photoUser.collect { photo ->
            setElements(photo)
          }
        }
      }
    }
  }
}

