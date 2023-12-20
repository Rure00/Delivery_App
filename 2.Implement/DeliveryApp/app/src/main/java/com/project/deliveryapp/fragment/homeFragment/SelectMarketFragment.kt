package com.project.deliveryapp.fragment.homeFragment

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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.project.deliveryapp.R
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.activity.TabTag
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.databinding.FragmentSelectMarketBinding
import com.project.deliveryapp.fragment.MarketScoreFragment
import com.project.deliveryapp.fragment.ShoppingFragment
import com.project.deliveryapp.view_model.MainViewModel
import java.lang.Math.round

class SelectMarketFragment : Fragment() {
    private var _binding: FragmentSelectMarketBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel
    private lateinit var curMarket: MarketData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        curMarket = viewModel.getCurMarket()

        mainActivity = requireActivity() as MainActivity

        mainActivity.onBackPressedDispatcher.addCallback(this) {
            Log.d("on Back Button Pressed", "It's SelectMarketFragment")
            requireParentFragment().childFragmentManager.beginTransaction().replace(R.id.container,RecentMarketFragment()).commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentSelectMarketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentFragment = requireParentFragment()
        with(binding) {
            marketAddress.text = curMarket.address
            marketDescription.text = curMarket.description
            marketNameText.text = curMarket.name
            marketScoreBtn.text = String.format(getString(R.string.score_expression), curMarket.score)

            marketScoreBtn.setOnClickListener {
                mainActivity.pushFragments(TabTag.TAB_FIND, MarketScoreFragment(), true)
            }
            orderButton.setOnClickListener {
                mainActivity.pushFragments(TabTag.TAB_FIND, ShoppingFragment(), true)
            }
            backBtn.setOnClickListener {
                parentFragment.childFragmentManager.beginTransaction().replace(R.id.container,RecentMarketFragment()).commit()
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}