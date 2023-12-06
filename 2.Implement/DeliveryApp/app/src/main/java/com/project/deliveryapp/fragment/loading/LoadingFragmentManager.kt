package com.project.deliveryapp.fragment.loading

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

class LoadingFragmentManager() {
    companion object {
        private val circleLoadingFragment = CircleLoadingFragment()
    }

    fun circleLoading(fragmentManager: FragmentManager, @IdRes containerViewId: Int) {
        val transaction = fragmentManager.beginTransaction()

        transaction.add(containerViewId, circleLoadingFragment)
        transaction.commit()
    }
    fun cancelLoading(fragmentManager: FragmentManager, @IdRes containerViewId: Int) {
        val transaction = fragmentManager.beginTransaction()

        transaction.remove(circleLoadingFragment)
        transaction.commit()
    }
}