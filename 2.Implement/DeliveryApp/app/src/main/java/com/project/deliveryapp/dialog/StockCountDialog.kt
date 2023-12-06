package com.project.deliveryapp.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.project.deliveryapp.databinding.StockCountDialogBinding

class StockCountDialog(): DialogFragment() {
    private var _binding: StockCountDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var buttonClickListener: OnButtonClickListener
    val itemCount: Int get() = binding.countText.text.toString().toInt()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = StockCountDialogBinding.inflate(inflater, container, false)

        isCancelable = true
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            confirm.setOnClickListener {
                buttonClickListener.onConfirmButtonClicked()
            }
            cancel.setOnClickListener {
                buttonClickListener.onCancelButtonClicked()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.6).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams



    }

    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }
    interface OnButtonClickListener{
        fun onConfirmButtonClicked()
        fun onCancelButtonClicked()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}