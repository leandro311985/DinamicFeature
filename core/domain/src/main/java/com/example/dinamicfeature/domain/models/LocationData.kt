package com.example.dinamicfeature.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LocationData (
  var latitude: Double? = 0.0,
  var longitude: Double? = 0.0
) : Parcelable
