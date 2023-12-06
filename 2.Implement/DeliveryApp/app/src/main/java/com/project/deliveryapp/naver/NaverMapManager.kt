package com.project.deliveryapp.naver

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions

object NaverMapManager{
    //Options
    private const val minZoomScale = 14.0
    private const val maxZoomScale = 18.0
    private val disabledLayers = arrayOf(
                NaverMap.LAYER_GROUP_TRAFFIC,
                NaverMap.LAYER_GROUP_BICYCLE,
                NaverMap.LAYER_GROUP_MOUNTAIN,
                NaverMap.LAYER_GROUP_CADASTRAL)
    private const val isTiltEnabled = false
    private const val isRotateEnabled = false

    private val OPTION = NaverMapOptions()
        .minZoom(minZoomScale)
        .maxZoom(maxZoomScale)
        .disabledLayerGroups(*disabledLayers)
        .tiltGesturesEnabled(isTiltEnabled)
        .rotateGesturesEnabled(isRotateEnabled)

    fun displayMap(fm: FragmentManager, @IdRes id: Int): MapFragment
         = MapFragment.newInstance(OPTION).also {
        fm.beginTransaction().add(id, it).commit()
    }


}