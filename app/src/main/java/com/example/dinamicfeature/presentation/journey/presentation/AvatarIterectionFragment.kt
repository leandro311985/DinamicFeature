package com.example.dinamicfeature.presentation.journey.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentAvatarIterctionBinding

class AvatarIterectionFragment : BaseFragment(R.layout.fragment_avatar_iterction) {

  private lateinit var binding: FragmentAvatarIterctionBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElements()
    setVideo()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentAvatarIterctionBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    btnNext.setOnClickListener {
      findNavController().navigate(AvatarIterectionFragmentDirections.actionNavigationStep3ToNavigationStep4())
    }
  }

  private fun setVideo() = binding.apply {
    val packageName = context?.packageName
    val videoPath = "android.resource://" + packageName + "/" + R.raw.avatar3
    videoView.setVideoURI(Uri.parse(videoPath))

    videoView.setVideoPath(videoPath)
    videoView.start()
  }
}