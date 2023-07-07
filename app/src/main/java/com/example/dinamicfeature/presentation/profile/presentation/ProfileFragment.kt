package com.example.dinamicfeature.presentation.profile.presentation

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentProfileBinding
import com.example.dinamicfeature.domain.models.Profile
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.presentation.home.presentation.PhotoPagerAdapter
import com.example.dinamicfeature.presentation.profile.presentation.adapter.ProfileAdapter
import com.example.dinamicfeature.presentation.profile.presentation.adapter.ProfileImageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.util.*
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
    binding.containerToolbarProfile.title.text = "Meu Perfil"
    binding.containerToolbarProfile.icToolbarMenu.isVisible = true
    setElements()
    viewModel.getLocation()
    viewModel.getMyList()
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
    imageProfileViewPage.isVisible = !isVisible
    rcTimeLine.isVisible = !isVisible
    cardView1.isVisible = !isVisible
    cardView2.isVisible = !isVisible
    cardView3.isVisible = !isVisible
    containerUser.view.isVisible = !isVisible
    containerUser.logoImageView.isVisible = !isVisible
    containerUser.name.isVisible = !isVisible
    containerUser.iconeLocation.isVisible = !isVisible
    caed1.isVisible = !isVisible
    card2.isVisible = !isVisible
    caed3.isVisible = !isVisible
    card4.isVisible = !isVisible
  }

  private fun getPhotoFirebase(userId:String){
    database = Firebase.database.reference
    database.child("users").child(userId).get().addOnSuccessListener {
//      setElements(it.value.toString())
    }.addOnFailureListener{
      Log.e("firebase", "Error getting data", it)
    }
  }

  private fun setElements() = binding.apply {

    containerToolbarProfile.icToolbarMenu.setOnClickListener {
      findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileFragmentToConfigFragment())
    }

    val photo = listOf(R.drawable.profile3, R.drawable.profile, R.drawable.profile1)
    imageProfileViewPage.apply {
      layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
      val adapterViewPager = ProfileImageAdapter(requireContext(), photo)
      imageProfileViewPage.adapter = adapterViewPager
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

        launch {
          viewModel.location.collect { location ->
            getNeighborhoodFromLocation(location.latitude?:0.0,location.longitude?:0.0)
          }
        }

        launch {
          viewModel.myListLikeSize.collect { size ->
           binding.sizeLike.text = size.toString()
          }
        }
        launch {
          viewModel.myListHomeTalvez.collect { size ->
           binding.infoTextNumber3.text = size.toString()
          }
        }
      }
    }
  }

  private fun getNeighborhoodFromLocation(latitude: Double, longitude: Double) {
    val geocoder = Geocoder(requireContext(), Locale.getDefault())
    val addresses: MutableList<Address> = geocoder.getFromLocation(latitude, longitude, 1) as MutableList<Address>

    if (addresses.isNotEmpty()) {
      val address: Address = addresses[0]
      binding.containerUser.local.text = address.subLocality ?: ""
    }

  }
}

