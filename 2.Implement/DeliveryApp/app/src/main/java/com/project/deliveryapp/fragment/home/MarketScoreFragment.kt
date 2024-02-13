package com.project.deliveryapp.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import kotlinx.coroutines.withContext

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
            curMarketData = viewModel.getMarketData(context, viewModel.curMarketId) ?: return@launch
            reviews = viewModel.getReviews(context, curMarketData.id)

            withContext(Dispatchers.Main) {
                binding.marketNameText.text = curMarketData.name
                binding.addressText.text = curMarketData.address
                binding.scoreText.text = getString(R.string.score_expression, curMarketData.score)

                if(reviews.isNotEmpty()) {
                    val adapter = ReviewRvAdapter(reviews)
                    adapter.notifyItemRangeInserted(0, reviews.size)

                    val recyclerView = binding.recyclerView
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    adapter.itemClickListener = object: ReviewRvAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            TODO("Not yet implemented")

                        }

                        override fun onReportClick(position: Int) {
                            Toast.makeText(context, "구현 예정인 기능입니다.", Toast.LENGTH_SHORT).show()
                        }
                        override fun onDeleteClick(position: Int) {
                            Toast.makeText(context, "구현 예정인 기능입니다.", Toast.LENGTH_SHORT).show()
                        }

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