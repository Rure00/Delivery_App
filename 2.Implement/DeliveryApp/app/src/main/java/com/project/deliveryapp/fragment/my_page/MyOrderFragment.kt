package com.project.deliveryapp.fragment.my_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.activity.TabTag
import com.project.deliveryapp.data.Order
import com.project.deliveryapp.databinding.FragmentMyOrderBinding
import com.project.deliveryapp.recycler_view.OrderRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyOrderFragment : Fragment() {

    private var _binding: FragmentMyOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var orderList: ArrayList<Order>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mainActivity.onBackPressedDispatcher.addCallback() {
            mainActivity.popFragments()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMyOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            orderList = viewModel.getMyOrder()

            if(orderList.isNotEmpty()) {
                val adapter = OrderRvAdapter(orderList)
                adapter.notifyItemRangeInserted(0, orderList.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                adapter.setOnClickListener(object : OrderRvAdapter.OnItemClickListener {
                    override fun onCheckButtonClick(position: Int) {
                        viewModel.saveCurOrder(orderList[position])
                        mainActivity.pushFragments(TabTag.TAB_MY_PAGE, MyOrderDetailFragment(), true)
                    }
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}