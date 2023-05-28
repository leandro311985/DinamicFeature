package com.example.dinamicfeature.login.facebook

import android.os.Bundle
import android.view.View
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.login.R
import com.google.firebase.auth.FirebaseAuth

class FaceBookLoginFragment : BaseFragment(R.layout.fragment_login) {

  private lateinit var auth: FirebaseAuth

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    TODO("Not yet implemented")
  }

  override fun setViewBinding(view: View) {
    TODO("Not yet implemented")
  }
}


