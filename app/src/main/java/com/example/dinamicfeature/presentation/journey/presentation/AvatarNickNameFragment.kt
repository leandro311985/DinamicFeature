package com.example.dinamicfeature.presentation.journey.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentNickNameAvatarBinding

class AvatarNickNameFragment : BaseFragment(R.layout.fragment_nick_name_avatar) {

  private lateinit var binding: FragmentNickNameAvatarBinding
  private var avatarArgs = ""
  private val args: AvatarNickNameFragmentArgs by navArgs()



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
    checkArgs()
    setAvatar()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentNickNameAvatarBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    textTitle.setOnClickListener { findNavController().navigateUp() }
    btnNext.setOnClickListener {
      findNavController().navigate(AvatarNickNameFragmentDirections.actionStep2ToNavigationStep3()) }
  }

  private fun checkArgs() {
    avatarArgs = args.avatar
  }

  private fun setAvatar() = binding.apply{
    when(avatarArgs){
      "1" ->  avatar.setImageResource(R.drawable.avat)
      "2" ->  avatar.setImageResource(R.drawable.avat1)
      "3" ->  avatar.setImageResource(R.drawable.avat2)
      "4" ->  avatar.setImageResource(R.drawable.avat3)
      "5" ->  avatar.setImageResource(R.drawable.avat4)
      "6" ->  avatar.setImageResource(R.drawable.avat5)
      "7" ->  avatar.setImageResource(R.drawable.avatar7)
      "8" ->  avatar.setImageResource(R.drawable.avatar8)
    }

  }
}