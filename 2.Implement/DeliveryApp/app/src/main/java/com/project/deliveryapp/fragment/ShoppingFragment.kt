package com.project.deliveryapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.deliveryapp.R
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.data.ItemOnBuy
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.databinding.FragmentFindMarketBinding
import com.project.deliveryapp.databinding.FragmentShoppingBinding
import com.project.deliveryapp.dialog.StockCountDialog
import com.project.deliveryapp.recycler_view.ItemRvAdapter
import com.project.deliveryapp.recycler_view.ReviewRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ShoppingFragment : Fragment() {

    private var _binding: FragmentShoppingBinding? = null
    private val binding get() = _binding!!

    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var market: MarketData
    private lateinit var items: Array<Stock>
    private lateinit var cart: Cart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        market = viewModel.getCurMarket()
        cart = Cart(market)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentFragment = requireParentFragment()


        CoroutineScope(Dispatchers.IO).launch {
            items = viewModel.getStocks()

            if(items.isNotEmpty()) {
                cart = Cart(market)
                val adapter = ItemRvAdapter(items)
                adapter.notifyItemRangeInserted(0, items.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                adapter.itemClickListener = object: ItemRvAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                        val dialog = StockCountDialog()
                        val stock = items[position]

                        dialog.setButtonClickListener(object: StockCountDialog.OnButtonClickListener{
                            override fun onConfirmButtonClicked() {
                                val item = ItemOnBuy(stock, dialog.itemCount)
                                cart.add(item)

                                val formatter = DecimalFormat("###,###")
                                binding.expenseText.text = formatter.format(cart.expense)

                                dialog.dismiss()
                            }
                            override fun onCancelButtonClicked() {
                                dialog.dismiss()
                            }
                        })

                        dialog.show(parentFragmentManager, "StockCountDialog")
                    }
                }
            }

        }

        binding.toCartBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.saveCart(cart)
            }

            parentFragment.findNavController().navigate(R.id.action_shoppingFragment_to_cartFragment)
            parentFragment.childFragmentManager.beginTransaction().remove(this).commit()
        }
        binding.orderBtn.setOnClickListener {
            viewModel.saveCart(cart)
            parentFragment.findNavController().navigate(R.id.action_shoppingFragment_to_paymentFragment)
            parentFragment.childFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getStockCountDialog(stock: Stock): StockCountDialog {
        val dialog = StockCountDialog()

        dialog.setButtonClickListener(object: StockCountDialog.OnButtonClickListener{
            override fun onConfirmButtonClicked() {
                val item = ItemOnBuy(stock, dialog.itemCount)
                cart.add(item)

                val formatter = DecimalFormat("###,###")
                binding.expenseText.text = formatter.format(cart.expense)

                dialog.dismiss()
            }
            override fun onCancelButtonClicked() {
                dialog.dismiss()
            }
        })

        return dialog
    }

}