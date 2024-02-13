package com.project.deliveryapp.settings

import android.Manifest

object PermissionManager {
    const val GPS_REQUEST_CODE = 100

    private val _gpsPermission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )
    val gpsPermission
        get() = _gpsPermission
}