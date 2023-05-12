package com.example.dinamicfeature

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dinamicfeature.commons.BaseActivity
import com.example.dinamicfeature.databinding.ActivityMainBinding
import com.google.android.play.core.splitcompat.SplitCompat

class MainActivity : BaseActivity() {

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