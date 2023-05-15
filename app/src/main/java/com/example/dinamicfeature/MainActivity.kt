package com.example.dinamicfeature

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dinamicfeature.baseApp.common.BaseActivity
import com.example.dinamicfeature.databinding.ActivityMainBinding

class MainActivity : com.example.dinamicfeature.baseApp.common.BaseActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initView()
  }

  private fun initView() {
    setUiComponents()
  }


  private fun setUiComponents() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    navController = navHostFragment.navController
  }

}