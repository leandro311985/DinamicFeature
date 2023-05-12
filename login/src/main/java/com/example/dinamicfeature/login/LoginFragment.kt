package com.example.dinamicfeature.login

import android.view.View
import com.example.dinamicfeature.commons.BaseFragment
import com.example.dinamicfeature.login.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment(R.layout.fragment_login) {

  private lateinit var binding: FragmentLoginBinding

  override fun initView(view: View) {
    setViewBinding(view)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentLoginBinding.bind(view)
  }
}