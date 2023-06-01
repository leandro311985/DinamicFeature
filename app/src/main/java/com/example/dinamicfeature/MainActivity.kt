package com.example.dinamicfeature

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dinamicfeature.baseApp.commons.BaseActivity
import com.example.dinamicfeature.databinding.ActivityMainBinding

class MainActivity : BaseActivity() , LocationListener {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController
  private val topLevelDestination = mutableListOf<String>()

  private lateinit var locationManager: LocationManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initView()
  }

  private fun initView() {
    setTopLevelDestinations()
    setUiComponents()
    setupNavigation()
    requestPermission()
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp() || super.onSupportNavigateUp()
  }

  private fun setUiComponents() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    navController = navHostFragment.navController
  }

  private fun setTopLevelDestinations() {
    topLevelDestination.add(getString(R.string.title_mysignature))
    topLevelDestination.add(getString(R.string.title_profile))
    topLevelDestination.add(getString(R.string.title_searchpeople))
  }

  private fun setupNavigation() {
    binding.bottomNavigation.setupWithNavController(navController)
    setupWithNavController(binding.bottomNavigation, navController)

    navController.addOnDestinationChangedListener { _, destination, _ ->
      binding.bottomNavigation.isVisible =
        topLevelDestination.contains(destination.label)
    }
  }

  private fun requestPermission() {
    locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

    // Verifica se a permissão de localização foi concedida pelo usuário
    if (ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED &&
      ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      // Solicita a permissão de localização ao usuário
      ActivityCompat.requestPermissions(
        this,
        arrayOf(
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        REQUEST_LOCATION_PERMISSION
      )
    } else {
      // Permissão já concedida, solicita atualizações de localização
      locationManager.requestLocationUpdates(
        LocationManager.GPS_PROVIDER,
        MIN_TIME_BETWEEN_UPDATES,
        MIN_DISTANCE_CHANGE_FOR_UPDATES,
        this
      )
    }
  }

  override fun onLocationChanged(location: Location) {
    // Aqui você pode acessar a posição atual do usuário através do objeto 'location'
    val latitude = location.latitude
    val longitude = location.longitude
  }

  override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

  override fun onProviderEnabled(provider: String) {}

  override fun onProviderDisabled(provider: String) {}

  companion object {
    private const val REQUEST_LOCATION_PERMISSION = 123
    private const val MIN_TIME_BETWEEN_UPDATES: Long = 1000 // Intervalo mínimo de tempo entre atualizações em milissegundos
    private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10.0f // Distância mínima em metros para atualizações
  }

}