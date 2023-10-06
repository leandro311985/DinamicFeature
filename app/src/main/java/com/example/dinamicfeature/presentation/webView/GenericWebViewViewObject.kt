package com.example.dinamicfeature.presentation.webView

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenericWebViewViewObject(
  var title: String? = null,
  var url: String = "https://campsite.bio/espiritualidadeativa?fbclid=PAAaZ0EvjwD5rL8Sytcx6xZHR3EdSzA6Ji5Z7dpDDx5fKz2PFqHLXYq2nFjJY"
): Parcelable
