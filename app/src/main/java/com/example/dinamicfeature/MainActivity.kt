package com.example.dinamicfeature

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dinamicfeature.baseApp.commons.BaseActivity
import com.example.dinamicfeature.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController
  private val topLevelDestination = mutableListOf<String>()


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
    topLevelDestination.add(getString(R.string.HOME))
    topLevelDestination.add(getString(R.string.HOME_PRODUCT_HOME_FRAGMENT_LABEL))
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

}