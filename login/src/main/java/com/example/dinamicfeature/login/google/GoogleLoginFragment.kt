package com.example.dinamicfeature.login.google

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.login.R
import com.example.dinamicfeature.login.databinding.FragmentLoginBinding
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GoogleLoginFragment : BaseFragment(R.layout.fragment_login) {

  private lateinit var auth: FirebaseAuth
  private lateinit var binding: FragmentLoginBinding

  private val REQ_ONE_TAP = 2
  private var showOneTapUI = true


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    auth = Firebase.auth
  }

  override fun setViewBinding(view: View) {
    binding = FragmentLoginBinding.bind(view)
  }

}