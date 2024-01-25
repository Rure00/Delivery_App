package com.project.deliveryapp.fragment.my_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.deliveryapp.R
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.data.Order
import com.project.deliveryapp.databinding.FragmentCartDetailBinding
import com.project.deliveryapp.databinding.FragmentMyOrderDetailBinding
import com.project.deliveryapp.view_model.MainViewModel

class MyOrderDetailFragment : Fragment() {
    private var _binding: FragmentMyOrderDetailBinding? = null
    private val binding: FragmentMyOrderDetailBinding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var curOrder: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMyOrderDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}