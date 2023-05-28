package com.example.dinamicfeature.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

  private lateinit var binding: FragmentHomeBinding
  private val viewModel: HomeViewModel by viewModel()
  private lateinit var fusedLocationClient: FusedLocationProviderClient


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setLoading(true)
    checkPermission()
    setElement()
    setCollectors()

  }

  override fun setViewBinding(view: View) {
    binding = FragmentHomeBinding.bind(view)
  }

  private fun checkPermission() {
    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
    ) {
      getLastLocation()
    } else {
      ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION), 1)
    }
  }

  private fun setLoading(isVisible: Boolean) {
    binding.loadingContainerHome.isVisible = isVisible
  }

  private fun setElement() = binding.apply {
    lifecycleScope.launch {
      delay(2000)
      setLoading(false)
    }
    btnHome.setOnClickListener {
      viewModel.logout()
    }

  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.logout.collect { result ->
            if (result) Toast.makeText(requireContext(), "desconctado", Toast.LENGTH_SHORT).show()
            activity?.finish()
          }
        }
        launch {

        }
      }
    }
  }

  private fun textLocation(latitude: String, longitude: String) {
    binding.textLocation.text = "$latitude e $longitude"
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      // Permissão concedida, podemos obter a localização
      getLastLocation()
    }
  }

  private fun getLastLocation() {
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
          if (location != null) {
            textLocation(location.latitude.toString(), location.longitude.toString())
          }
        }
        .addOnFailureListener { exception ->
          val erro = exception.printStackTrace()
          Toast.makeText(requireContext(), erro.toString(), Toast.LENGTH_SHORT).show()
        }
    }
  }

  private suspend fun getCurrentLocation() {
    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      val result = fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        CancellationTokenSource().token
      ).await()
      result?.let { fetchedLocation ->
        var locationInfo =
          "Current location is \n" + "lat : ${fetchedLocation.latitude}\n" +
            "long : ${fetchedLocation.longitude}\n" + "fetched at ${System.currentTimeMillis()}"

        Toast.makeText(requireContext(), locationInfo, Toast.LENGTH_SHORT).show()

      }

    } else {

    }
  }
}