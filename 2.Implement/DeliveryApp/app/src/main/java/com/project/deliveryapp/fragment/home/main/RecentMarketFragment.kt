package com.project.deliveryapp.fragment.home.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.deliveryapp.R
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.databinding.FragmentRecentMarketBinding
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.recycler_view.RecentMarketRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentMarketFragment : Fragment() {
    private var _binding: FragmentRecentMarketBinding? = null
    private val binding get() = _binding!!
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private var recentMarketData: List<MarketData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecentMarketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoadingDialog.show(context)
        CoroutineScope(Dispatchers.IO).launch {
            recentMarketData = viewModel.getRecentMarketInfo(context)

            recentMarketData?.let{
                if(it.isEmpty()) {
                    return@let
                }

                binding.noRecentText.isVisible = false
                binding.recyclerViewLayout.isVisible = true

                val adapter = RecentMarketRvAdapter(it)
                adapter.notifyItemRangeInserted(0, it.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                adapter.itemClickListener = object: RecentMarketRvAdapter.OnItemClickListener{
                    override fun onClick(position: Int) {
                        val recentMarket = recentMarketData!![position]

                        viewModel.curMarketId = recentMarket.id
                        requireParentFragment().childFragmentManager.beginTransaction()
                            .replace(R.id.container, SelectMarketFragment()).commit()
                    }
                }
            }

            LoadingDialog.dismiss()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        //TODO: Add a bellow code in all fragments
        _binding = null
    }

}