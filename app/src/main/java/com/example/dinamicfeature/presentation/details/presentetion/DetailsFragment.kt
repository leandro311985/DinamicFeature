package com.example.dinamicfeature.presentation.details.presentetion

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentDetailsBinding
import com.example.extension.openLargeImage
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class DetailsFragment : BaseFragment(R.layout.fragment_details) {

  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()
  private lateinit var photoId: String


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    checkArgs()
    setImage()

  }

  override fun setViewBinding(view: View) {
    binding = FragmentDetailsBinding.bind(view)
  }

  private fun checkArgs() {
    photoId = args.photo
  }

  private fun setImage()  {
    when (photoId) {
      "1" -> setImgDrawable(R.drawable.woman)
      "2" -> setImgDrawable(R.drawable.woman2)
      "3" -> setImgDrawable(R.drawable.woman3)
      "4" -> setImgDrawable(R.drawable.woman)
    }

  }

  private fun setImgDrawable(image: Int) {
    val target = object : Target {
      override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        // Aqui você pode realizar a ação desejada com a imagem carregada
        binding.imageViewPhoto.setImageBitmap(bitmap)
      }

      override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        // Tratamento de erro, se necessário
      }

      override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        // Ação a ser executada enquanto a imagem está sendo carregada
      }
    }

    openLargeImage(image, target)

}}