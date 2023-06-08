package com.example.extension

import android.content.Context
import android.util.AttributeSet
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class SquaredImageView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    setMeasuredDimension(measuredWidth, measuredWidth)
  }
}

fun openLargeImage(imageUrl: Int, target: Target) {
  Picasso.get()
    .load(imageUrl)
    .resize(1080, 1920)
    .centerCrop()// Define a largura desejada (1080 pixels) e mantém a altura proporcional
    .onlyScaleDown() // Redimensiona apenas se a imagem original for maior do que a largura especificada
    .into(target)
}