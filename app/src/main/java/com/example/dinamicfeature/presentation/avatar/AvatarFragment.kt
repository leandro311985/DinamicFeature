package com.example.dinamicfeature.presentation.avatar

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentAvatarBinding

class AvatarFragment : BaseFragment(R.layout.fragment_avatar) {


  private lateinit var binding: FragmentAvatarBinding


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    binding.containerToolbarAvatar.title.text = "Ajuda"
    setElements()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentAvatarBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    val packageName = context?.packageName
    val videoPath = "android.resource://" + packageName + "/" + R.raw.avatar
    videoView.setVideoURI(Uri.parse(videoPath))

    videoView.setVideoPath(videoPath)
    videoView.start()
  }
}