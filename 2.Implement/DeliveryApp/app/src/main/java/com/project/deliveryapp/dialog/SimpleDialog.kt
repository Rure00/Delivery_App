package com.project.deliveryapp.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.project.deliveryapp.databinding.SimpleDialogBinding

class SimpleDialog(
    private val title: String,
    private val body: String,
    private val confirmButtonText: String = "확인",
    private val cancelButtonText: String = "취소"
): DialogFragment() {

    private lateinit var buttonClickListener: OnButtonClickListener
    private var _binding: SimpleDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = SimpleDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root

        with(binding) {
            titleText.text = title
            bodyText.text = body
            confirmBtn.text = confirmButtonText
            cancelBtn.text = cancelButtonText

            confirmBtn.setOnClickListener {
                buttonClickListener.onConfirmButtonClicked()
            }
            cancelBtn.setOnClickListener {
                buttonClickListener.onCancelButtonClicked()
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams


    }

    interface OnButtonClickListener {
        fun onConfirmButtonClicked()
        fun onCancelButtonClicked()
    }
    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }

}