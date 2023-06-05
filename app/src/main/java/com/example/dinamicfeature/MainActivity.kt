package com.example.dinamicfeature

import android.Manifest
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.BuildConfig
import com.example.dinamicfeature.baseApp.commons.BaseActivity
import com.example.dinamicfeature.commons.ForegroundOnlyLocationService
import com.example.dinamicfeature.databinding.ActivityMainBinding
import com.example.dinamicfeature.domain.models.LocationData
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

class MainActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding
  private val viewModel: AppViewModel by viewModel()

  private lateinit var navController: NavController
  private lateinit var drawerLayout: DrawerLayout
  private lateinit var appBarConfiguration: AppBarConfiguration
  private val topLevelDestination = mutableListOf<String>()
  private val topLevelLocation = mutableListOf<String>()
  private var foregroundOnlyLocationServiceBound = false

  private var foregroundOnlyLocationService: ForegroundOnlyLocationService? = null
  private lateinit var foregroundOnlyBroadcastReceiver: ForegroundOnlyBroadcastReceiver

  private val foregroundOnlyServiceConnection = object : ServiceConnection {

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
      val binder = service as ForegroundOnlyLocationService.LocalBinder
      foregroundOnlyLocationService = binder.service
      foregroundOnlyLocationServiceBound = true
    }

    override fun onServiceDisconnected(name: ComponentName) {
      foregroundOnlyLocationService = null
      foregroundOnlyLocationServiceBound = false
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initView()
  }

  private fun initView() {
    getLocation()
    setTopLevelDestinations()
    topLocation()
    setUiComponents()
    setupNavigation()
    setCollectors()
  }

  private fun hideActionBar(isHide:Boolean){
    val action = supportActionBar
    if (isHide)  action?.hide() else  action?.show()
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)
  }

  override fun onSupportNavigateUp(): Boolean {
    // Abre o Navigation Drawer quando o ícone de menu for clicado
    drawerLayout.openDrawer(binding.navigationView)
    return true
  }

  private fun setUiComponents() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    navController = navHostFragment.navController
  }

  private fun setTopLevelDestinations() {
    topLevelDestination.add(getString(R.string.SPLASH))
    topLevelDestination.add(getString(R.string.title_login))
    topLevelDestination.add(getString(R.string.title_createUser))
  }

  private fun setupNavigation() {
    binding.navigationView.setupWithNavController(navController)
    drawerLayout = binding.activityMain
    setupWithNavController(binding.navigationView, navController)
    appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
    setupActionBarWithNavController(navController, appBarConfiguration)

    navController.addOnDestinationChangedListener { _, destination, _ ->

      if (topLevelDestination.contains(destination.label)){
        hideActionBar(true)
      }else{
        hideActionBar(false)
      }
      if (topLevelLocation.contains(destination.label)) {
        subscribeLocationService()
      } else {
        unsubscribeLocationService()
      }
    }
  }

  private fun topLocation() {
    topLevelLocation.add(getString(R.string.title_searchpeople))
  }

  private fun getLocation() {
    foregroundOnlyBroadcastReceiver = ForegroundOnlyBroadcastReceiver()
  }

  private fun unsubscribeLocationService() {
    foregroundOnlyLocationService?.unsubscribeToLocationUpdates()
  }

  private fun subscribeLocationService() {
    if (foregroundPermissionApproved()) {
      foregroundOnlyLocationService?.subscribeToLocationUpdates()
        ?: Log.d(TAG, "Service Not Bound")
    } else {
      requestForegroundPermissions()
    }
  }

  override fun onStart() {
    super.onStart()

    val serviceIntent = Intent(this, ForegroundOnlyLocationService::class.java)
    bindService(serviceIntent, foregroundOnlyServiceConnection, Context.BIND_AUTO_CREATE)
  }

  override fun onResume() {
    super.onResume()
    LocalBroadcastManager.getInstance(this).registerReceiver(
      foregroundOnlyBroadcastReceiver,
      IntentFilter(
        ForegroundOnlyLocationService.ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST
      )
    )
  }

  override fun onPause() {
    LocalBroadcastManager.getInstance(this).unregisterReceiver(
      foregroundOnlyBroadcastReceiver
    )
    super.onPause()
  }

  override fun onStop() {
    if (foregroundOnlyLocationServiceBound) {
      unbindService(foregroundOnlyServiceConnection)
      foregroundOnlyLocationServiceBound = false
    }

    super.onStop()
  }

  private fun foregroundPermissionApproved(): Boolean {
    return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
      this,
      Manifest.permission.ACCESS_FINE_LOCATION
    )
  }

  private fun requestForegroundPermissions() {
    val provideRationale = foregroundPermissionApproved()

    // Se o usuário negou uma solicitação anterior, mas não marcou "Não pergunte novamente", forneça
    // justificativa adicional.
    if (provideRationale) {
      Snackbar.make(
        findViewById(R.id.activity_main),
        R.string.permission_rationale,
        Snackbar.LENGTH_LONG
      )
        .setAction(R.string.ok) {
          // Request permission
          ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
          )
        }
        .show()
    } else {
      Log.d(TAG, "Request foreground only permission")
      ActivityCompat.requestPermissions(
        this@MainActivity,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
      )
    }
  }

  // TODO: Step 1.0, Review Permissions: Handles permission result.
  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    Log.d(TAG, "onRequestPermissionResult")

    when (requestCode) {
      REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE -> when {
        grantResults.isEmpty() ->
          // Se a interação do usuário foi interrompida, a solicitação de permissão
          // é cancelado e você recebe matrizes vazias.
          Log.d(TAG, "User interaction was cancelled.")
        grantResults[0] == PackageManager.PERMISSION_GRANTED ->
          // Permission was granted.
          foregroundOnlyLocationService?.subscribeToLocationUpdates()
        else -> {

          Snackbar.make(
            binding.activityMain,
            R.string.permission_denied_explanation,
            Snackbar.LENGTH_LONG
          )
            .setAction(R.string.settings) {
              // Build intent that displays the App settings screen.
              val intent = Intent()
              intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
              val uri = Uri.fromParts(
                "com.example.dinamicfeature",
                BuildConfig.LIBRARY_PACKAGE_NAME,
                null
              )
              intent.data = uri
              intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
              startActivity(intent)
            }
            .show()
        }
      }
    }
  }

  private fun logResultsToScreen(latitude: Double, longitude: Double) {
    viewModel.saveLocationDb(LocationData(latitude, longitude))
  }

  /**
   * Receiver for location broadcasts from [ForegroundOnlyLocationService].
   */
  private inner class ForegroundOnlyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
      val location = intent.getParcelableExtra<Location>(
        ForegroundOnlyLocationService.EXTRA_LOCATION
      )

      if (location != null) {
        logResultsToScreen(location.latitude, location.longitude)
      }
    }
  }

  private fun setCollectors() {
    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.locationSaved.collect { result ->

            }
          }
      }
    }
  }
}

