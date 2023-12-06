package com.project.deliveryapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.naver.maps.geometry.LatLng
import com.project.deliveryapp.R
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.data.Review
import com.project.deliveryapp.databinding.FragmentMarketScoreBinding
import com.project.deliveryapp.recycler_view.ItemRvAdapter
import com.project.deliveryapp.recycler_view.RecentMarketRvAdapter
import com.project.deliveryapp.recycler_view.ReviewRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketScoreFragment : Fragment() {

    private var _binding: FragmentMarketScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var curMarketData: MarketData
    private var reviews: List<Review>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMarketScoreBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            //TODO: 고치기
            curMarketData = MarketData(
                1, "삼성마트", 4.5f, "1420014", "사당동 어딘가", "안녕", LatLng(31.4, 123.4)
            )//viewModel.getMarketData()!!

            val result = viewModel.getReviews()

            binding.marketNameText.text = curMarketData.name
            binding.addressText.text = curMarketData.address
            binding.scoreText.text = getString(R.string.score_expression, curMarketData.score)

            result.reviews?.let {
                val adapter = ReviewRvAdapter(it, result.hasMyReview)
                adapter.notifyItemRangeInserted(0, it.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                adapter.itemClickListener = object: ReviewRvAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                        //when item is clicked...

                    }

                }
            }
        }


        binding.filterBtn.setOnClickListener {
            //TODO: add filter algorithm
        }
        binding.reviewBtn.setOnClickListener {
            findNavController().navigate(R.id.action_marketScoreFragment_to_reviewFragment)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}