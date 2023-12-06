package com.project.deliveryapp.settings

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionManager {
    const val GPS_REQUEST_CODE = 100

    private val _gpsPermission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )
    val gpsPermission
        get() = _gpsPermission
}