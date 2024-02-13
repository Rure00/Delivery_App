package com.project.deliveryapp.fragment.home

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
import com.project.deliveryapp.activity.TabTag
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.data.ItemOnBuy
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.databinding.FragmentShoppingBinding
import com.project.deliveryapp.dialog.SimpleDialog
import com.project.deliveryapp.dialog.StockCountDialog
import com.project.deliveryapp.recycler_view.ItemRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ShoppingFragment : Fragment() {

    private var _binding: FragmentShoppingBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var market: MarketData
    private lateinit var items: ArrayList<Stock>
    private lateinit var cart: Cart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]



        requireActivity().onBackPressedDispatcher.addCallback(this@ShoppingFragment) {
            getBackPressedDialog().show(parentFragmentManager, "getBackPressedDialog")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            market = viewModel.getMarketData(context, viewModel.curMarketId)!!
            cart = Cart(market)

            items = viewModel.getStocks(context, viewModel.curMarketId)

            if(items.isNotEmpty()) {
                cart = Cart(market)
                val adapter = ItemRvAdapter(items)
                adapter.notifyItemRangeInserted(0, items.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                adapter.itemClickListener = object: ItemRvAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                        val stock = items[position]
                        getStockCountDialog(stock).show(parentFragmentManager, "StockCountDialog")
                    }
                }
            }

        }

        binding.toCartBtn.setOnClickListener {
            if(cart.expense == 0) {
                Toast.makeText(context, "장바구니에 물건을 담아주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            CoroutineScope(Dispatchers.IO).launch {
                val cartId = viewModel.saveCart(context, cart)!!

                viewModel.curCartId = cartId
                mainActivity.toCartTab(TabTag.TAB_FIND, true)
            }
        }
        binding.orderBtn.setOnClickListener {
            if(cart.expense == 0) {
                Toast.makeText(context, "장바구니에 물건을 담아주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            CoroutineScope(Dispatchers.IO).launch {
                val cartId = viewModel.saveCart(context, cart)!!
                viewModel.curCartId = cartId

                mainActivity.startPaymentActivity(cartId)
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    fun getTabPressedDialog(): SimpleDialog {
        val title = "취소할까요?"
        val body = "해당 내용은 저장되지 않아요. 그래도 괜찮을까요?"
        val confirmText = "괜찮아요!"
        val cancelText = "잠시만요!"

        return SimpleDialog(
            title, body, confirmText, cancelText
        )
    }
    private fun getBackPressedDialog(): SimpleDialog {
        val title = "취소하고 돌아갈까요?"
        val body = "해당 내용은 저장되지 않아요. 그래도 괜찮을까요?"
        val confirmText = "괜찮아요!"
        val cancelText = "잠시만요!"

        val backPressedDialog = SimpleDialog(
            title, body, confirmText, cancelText
        )

        backPressedDialog.setButtonClickListener(object: SimpleDialog.OnButtonClickListener{
            override fun onConfirmButtonClicked() {
                backPressedDialog.dismiss()
                mainActivity.popFragments()
            }
            override fun onCancelButtonClicked() {
                backPressedDialog.dismiss()
            }

        })

        return backPressedDialog
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