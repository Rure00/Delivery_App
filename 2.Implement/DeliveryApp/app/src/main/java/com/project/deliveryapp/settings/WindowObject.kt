package com.project.deliveryapp.settings

import android.content.Context
import android.util.DisplayMetrics

object WindowObject {
    private var widthPixels: Int = 0
    private var heightPixels: Int = 0

    val width: Int get() {
        if(widthPixels==0) throw Exception("Width is Not Set")
        return widthPixels
    }
    val height: Int get() {
        if(heightPixels==0) throw Exception("Width is Not Set")
        return heightPixels
    }

    fun setPixels(context: Context) {
        val metrics = context.resources.displayMetrics
        widthPixels = metrics.widthPixels
        heightPixels = metrics.heightPixels
    }


}