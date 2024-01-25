package com.project.deliveryapp.dialog.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.project.deliveryapp.R

object LoadingDialog {
    private lateinit var dialog: Loading
    fun show(context: Context) {
        dialog = Loading(context)
        dialog.show()
    }
    fun dismiss() {
        dialog.dismiss()
    }


}