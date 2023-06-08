package com.example.dinamicfeature.searchpeople.presentation


import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentSearchPeopleBinding
import com.example.dinamicfeature.domain.models.LocationData
import com.example.dinamicfeature.domain.models.UserFirebase
import com.example.dinamicfeature.searchpeople.SearchFragmentDirections
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.util.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment(R.layout.fragment_search_people) {

  private lateinit var binding: FragmentSearchPeopleBinding
  private val viewModel: SearchViewModel by viewModel()
  private var userData: UserFirebase? = null
  private var locationViewObject: LocationData? = null
  private lateinit var database: DatabaseReference


  override fun onResume() {
    super.onResume()
    viewModel.getPhoto()
    viewModel.getDataUser()
    viewModel.getLocation()
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

          }
        }
        launch {
          viewModel.location.collect { location ->
             getNeighborhoodFromLocation(location.latitude?:0.0,location.longitude?:0.0)
          }
        }
        launch {
          viewModel.userData.collect { dataUser ->
            userData = dataUser
            getPhotoFirebase(userData?.id?:"")
            var name = userData?.first_name?.toUpperCase()
            if (name == "") {
              binding.containerUserr.name.text = "Chuck norris"
            }else{
              binding.containerUserr.name.text = name
            }
          }
        }
      }
    }
  }

  private fun setElements(photo: String) = binding.apply {
    locationViewObject = LocationData()

    containerWoman.cardView3.setOnClickListener {
      val action = SearchFragmentDirections.actionSearchToNavigationDetails("3")
      findNavController().navigate(action)
    }
    containerWoman.cardView1.setOnClickListener {
      val action = SearchFragmentDirections.actionSearchToNavigationDetails("1")
      findNavController().navigate(action)
    }
    containerWoman.cardView2.setOnClickListener {
      val action = SearchFragmentDirections.actionSearchToNavigationDetails("2")
      findNavController().navigate(action)
    }
    containerWoman.cardView4.setOnClickListener {
      val action = SearchFragmentDirections.actionSearchToNavigationDetails("4")
      findNavController().navigate(action)
    }

    if (photo.isEmpty()) {
      Picasso.get()
        .load(R.drawable.ic_person)
        .into(containerUserr.logoImageView)
    } else {
      Picasso.get()
        .load(photo)
        .placeholder(R.drawable.ic_person)
        .into(containerUserr.logoImageView)
    }

  }

  private fun getNeighborhoodFromLocation(latitude: Double, longitude: Double) {
    val geocoder = Geocoder(requireContext(), Locale.getDefault())
    val addresses: MutableList<Address> = geocoder.getFromLocation(latitude, longitude, 1) as MutableList<Address>

    if (addresses.isNotEmpty()) {
      val address: Address = addresses[0]
      binding.containerUserr.local.text = address.subLocality ?: ""
    }

  }

  private fun getPhotoFirebase(userId:String){
    database = Firebase.database.reference
    database.child("users").child(userId).get().addOnSuccessListener {
      setElements(it.value.toString())
    }.addOnFailureListener{
      Log.e("firebase", "Error getting data", it)
    }
  }

}