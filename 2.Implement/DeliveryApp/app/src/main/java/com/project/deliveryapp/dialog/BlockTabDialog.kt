package com.project.deliveryapp.dialog

class BlockTabDialog {
    val title = "취소할까요?"
    val body = "해당 내용은 저장되지 않아요. 그래도 괜찮을까요?"
    val confirmText = "괜찮아요!"
    val cancelText = "잠시만요!"

    fun getDialog(): SimpleDialog {


        return SimpleDialog(title, body, confirmText, cancelText)
    }

}