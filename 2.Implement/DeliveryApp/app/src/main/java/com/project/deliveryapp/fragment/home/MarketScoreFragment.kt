package com.project.deliveryapp.fragment.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naver.maps.geometry.LatLng
import com.project.deliveryapp.R
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.activity.TabTag
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.data.dto.MarketReviewDto
import com.project.deliveryapp.databinding.FragmentMarketScoreBinding
import com.project.deliveryapp.recycler_view.ReviewRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketScoreFragment : Fragment() {

    private var _binding: FragmentMarketScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var curMarketData: MarketData
    private lateinit var reviews: ArrayList<MarketReviewDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        requireActivity().onBackPressedDispatcher.addCallback(this@MarketScoreFragment) {
            mainActivity.popFragments()
        }
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


            val obj = viewModel.getMarketData(context, viewModel.curCartId) ?: return@launch

            curMarketData = obj

            binding.marketNameText.text = curMarketData.name
            binding.addressText.text = curMarketData.address
            binding.scoreText.text = getString(R.string.score_expression, curMarketData.score)

            reviews = viewModel.getReviews(context, curMarketData.id)

            if(reviews.isNotEmpty()) {
                val adapter = ReviewRvAdapter(reviews)
                adapter.notifyItemRangeInserted(0, reviews.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                adapter.itemClickListener = object: ReviewRvAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                        //TODO: When A Review Item is Clicked

                    }

                }
            }
        }


        binding.filterBtn.setOnClickListener {
            //TODO: add a filter

        }
        binding.reviewBtn.setOnClickListener {
            mainActivity.pushFragments(TabTag.TAB_FIND, ReviewFragment(), true)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}