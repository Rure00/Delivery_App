package com.project.deliveryapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.activity.TabTag
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.databinding.FragmentCartBinding
import com.project.deliveryapp.dialog.SimpleDialog
import com.project.deliveryapp.recycler_view.CartRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var cartList: ArrayList<Cart>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        requireActivity().onBackPressedDispatcher.addCallback(this@CartFragment){
            mainActivity.toFindTab()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            cartList = viewModel.getCarts()

            if(cartList.isNotEmpty()) {
                val adapter = CartRvAdapter(cartList)
                adapter.notifyItemRangeInserted(0, cartList.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                adapter.setOnClickListener(object : CartRvAdapter.OnItemClickListener {
                    override fun onCheckButtonClick(position: Int) {
                        //TODO: navigate to CartDetailFragment
                        mainActivity.pushFragments(TabTag.TAB_CART, CartDetailFragment(), true)
                    }
                    override fun onRemoveButtonClick(position: Int) {
                        val removeCartDialog = getRemoveCartDialog()
                        removeCartDialog.show(parentFragmentManager, "RemoveCartDialog: SimpleDialog")
                        removeCartDialog.setButtonClickListener(object : SimpleDialog.OnButtonClickListener{
                            override fun onConfirmButtonClicked() {
                                viewModel.removeCart(cartList[position])
                                cartList.removeAt(position)

                                adapter.notifyItemRemoved(position)

                                removeCartDialog.dismiss()
                            }
                            override fun onCancelButtonClicked() {
                                removeCartDialog.dismiss()
                            }
                        })
                    }
                })
            }



        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun getRemoveCartDialog(): SimpleDialog {
        val title = "해당 장바구니를 지울까요?"
        val body = "다시 되돌릴 수 없어요."
        val confirmText = "지울게요!"
        val cancelText = "잠시만요!"

        return SimpleDialog(
            title = title,
            body = body,
            confirmButtonText = confirmText,
            cancelButtonText = cancelText
        )
    }
}