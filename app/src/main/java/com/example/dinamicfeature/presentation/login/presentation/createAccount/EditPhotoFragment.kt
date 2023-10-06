package com.example.dinamicfeature.presentation.login.presentation.createAccount

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentEditPhotoBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EditPhotoFragment : BaseFragment(R.layout.fragment_edit_photo) {

  private lateinit var binding: FragmentEditPhotoBinding
  private lateinit var imageFlow: MutableStateFlow<String?>
  private var uri_Imagem: Uri? = null
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    imageFlow = MutableStateFlow(null)
  }

  override fun setViewBinding(view: View) {
    binding = FragmentEditPhotoBinding.bind(view)
    setElements()
    setCollectors()
  }

  private fun setElements() = binding.apply {
    containerToolbar.title.text = "Editar Foto"
    containerToolbar.title.setTextColor(ContextCompat.getColor(requireContext(), R.color.DEFAULT_WHITE))
    containerToolbar.backgraund.setBackgroundColor(resources.getColor(R.color.DEFAULT_AZUL_APP_4))
    containerToolbar.img.setImageResource(R.drawable.baseline_white_arrow_back_ios_24)
    containerToolbar.img2.isVisible = false
    containerToolbar.img.setOnClickListener {
      findNavController().navigateUp()
    }

    cardView1.setOnClickListener {
      checkPermission()
    }

    nextButton.setOnClickListener {
      findNavController().navigateUp()
    }
  }

  private fun checkPermission() {
    if (ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.READ_EXTERNAL_STORAGE
      ) == PackageManager.PERMISSION_GRANTED
    ) {
      openGallery()
    } else {
      requestGalleryPermission()
    }
  }

  private fun requestGalleryPermission() {
    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
  }

  private fun openGallery() {
    getContentLauncher.launch("image/*")
  }

  private val requestPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { isGranted: Boolean ->
    if (isGranted) {
      openGallery()
    } else {
      // A permissão foi negada pelo usuário
      // Lide com isso de acordo com os requisitos do seu aplicativo
    }
  }

  private val getContentLauncher = registerForActivityResult(
    ActivityResultContracts.GetContent()
  ) { uri ->
    uri_Imagem = uri
    val imagePath = uri.toString()
    imageFlow.value = imagePath
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

        launch {
          imageFlow.collect { imagePath ->
            imagePath?.let { uri ->
              binding.img1.setImageURI(Uri.parse(uri))

            }
          }
        }
      }
    }
  }


}