package com.project.deliveryapp.fragment.cart

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.data.CartDetail
import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.databinding.FragmentCartDetailBinding
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.recycler_view.ItemRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartDetailFragment : Fragment() {
    private var _binding: FragmentCartDetailBinding? = null
    private val binding: FragmentCartDetailBinding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var cartDetail: CartDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mainActivity.onBackPressedDispatcher.addCallback {
            mainActivity.popFragments()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoadingDialog.show(context)
        CoroutineScope(Dispatchers.IO).launch {
            cartDetail = viewModel.getCartDetail(context, viewModel.curCartId)!!

            withContext(Dispatchers.Main) {
                val stocks = ArrayList<Stock>()
                val counts = ArrayList<Int>()
                cartDetail.itemsOnBuy.forEach {
                    stocks.add(it.stock)
                    counts.add(it.count)
                }

                val adapter = ItemRvAdapter(stocks, counts, true)
                adapter.notifyItemRangeInserted(0, stocks.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                Toast.makeText(context, "Total ${cartDetail.cost} won", Toast.LENGTH_SHORT).show()

                with(binding) {
                    expenseText.text = cartDetail.cost.toString()
                    marketName.text = cartDetail.marketName
                }

                LoadingDialog.dismiss()
            }
        }

        binding.orderBtn.setOnClickListener {
            mainActivity.startPaymentActivity(cartId = cartDetail.id)
        }
        binding.backBtn.setOnClickListener {
            mainActivity.popFragments()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}