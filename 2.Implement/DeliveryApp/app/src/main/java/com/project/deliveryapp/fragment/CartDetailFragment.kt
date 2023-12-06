package com.project.deliveryapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.deliveryapp.R
import com.project.deliveryapp.databinding.FragmentCartBinding
import com.project.deliveryapp.databinding.FragmentCartDetailBinding
import com.project.deliveryapp.view_model.MainViewModel

class CartDetailFragment : Fragment() {
    private var _binding: FragmentCartDetailBinding? = null
    private val binding: FragmentCartDetailBinding get() = _binding!!

    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        //TODO: 리사이클뷰 재사용 해보기
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartDetailBinding.inflate(inflater, container, false)

        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}