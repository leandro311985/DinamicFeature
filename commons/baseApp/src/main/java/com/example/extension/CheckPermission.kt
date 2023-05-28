package com.example.extension

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.isPermissionGranted(permission: String) = run {
  ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

//usage
//if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
//  // Block runs if permission is granted
//} else {
//  // Ask for permission
//}