package com.example.dinamicfeature.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient

object Location {

  private lateinit var fusedLocationClient: FusedLocationProviderClient
  private var latitude = 0.0
  private var longitude = 0.0


  fun getLastLocation(context: Context, result: (location :android.location.Location) -> Unit) {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
          if (location != null) {
            // Obteve a localização com sucesso
            latitude = location.latitude
            longitude = location.longitude
            result.invoke(location)
          }
        }
        .addOnFailureListener { exception ->
          // Não foi possível obter a localização
          exception.printStackTrace()
        }
    }

  }


}