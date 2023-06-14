package com.example.dinamicfeature.presentation.profile.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentProfileBinding
import com.example.dinamicfeature.domain.models.Profile
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.presentation.profile.presentation.adapter.ProfileAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

  private lateinit var binding: FragmentProfileBinding
  private lateinit var profileAdapter: ProfileAdapter
  private val viewModel: ProfileViewModel by viewModel()
  private var auth: FirebaseAuth? = null
  private lateinit var database: DatabaseReference


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
    loadingContainerProfile.loadingContainer.isVisible = isVisible
    imageProfile.isVisible = !isVisible
    rcTimeLine.isVisible = !isVisible
    cardView1.isVisible = !isVisible
    cardView2.isVisible = !isVisible
    cardView3.isVisible = !isVisible
  }

  private fun getPhotoFirebase(userId:String){
    database = Firebase.database.reference
    database.child("users").child(userId).get().addOnSuccessListener {
      setElements(it.value.toString())
    }.addOnFailureListener{
      Log.e("firebase", "Error getting data", it)
    }
  }

  private fun setElements(photo: String) = binding.apply {

    if (photo.isEmpty()) {
      return@apply
    } else {

      Picasso.get()
        .load(photo)
        .placeholder(R.drawable.ic_person)
        .into(imageProfile)
    }
  }


  private fun init(user: UserFirebase) {
    val profiles = listOf(
      Profile(ContextCompat.getDrawable(requireContext(), R.drawable.ic_user_bottom), "Carla Marine", "14 de Maio", "Conversou pelo chat as 19:00"),
      Profile(ContextCompat.getDrawable(requireContext(), R.drawable.ic_user_bottom), "Maria das graças", "27 de Maio", "Acabou de adicionar voçê a lista de favoritos"),
      Profile(ContextCompat.getDrawable(requireContext(), R.drawable.ic_user_bottom), "Ana Lurdes", "29 de Maio", "Acabou de adicionar voçê a lista de favoritos"),
      Profile(ContextCompat.getDrawable(requireContext(), R.drawable.ic_user_bottom), "Maria Joana", "08 de Maio", "Conversou pelo chat as 14:40"),
      Profile(ContextCompat.getDrawable(requireContext(), R.drawable.ic_user_bottom), "Paola Silva", "15 de Abril", "Acabou de adicionar voçê a lista de favoritos"),
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
            getPhotoFirebase(user.id)
            setLoading(false)
          }
        }
        launch {
          viewModel.photoUser.collect { photo ->
          }
        }
      }
    }
  }
}

