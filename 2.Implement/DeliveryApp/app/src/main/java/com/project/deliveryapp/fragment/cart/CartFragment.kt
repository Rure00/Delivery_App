package com.project.deliveryapp.fragment.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.activity.TabTag
import com.project.deliveryapp.data.dto.SimpleCartDto
import com.project.deliveryapp.databinding.FragmentCartBinding
import com.project.deliveryapp.dialog.SimpleDialog
import com.project.deliveryapp.recycler_view.CartRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: CartRvAdapter
    private lateinit var cartList: ArrayList<SimpleCartDto>


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

            cartList = viewModel.getMyCarts(context)

            withContext(Dispatchers.Main) {
                if(cartList.isNotEmpty()) {

                    adapter = CartRvAdapter(cartList)
                    adapter.notifyItemRangeInserted(0, cartList.size)

                    val recyclerView = binding.recyclerView
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    adapter.setOnClickListener(object : CartRvAdapter.OnItemClickListener {
                        override fun onCheckButtonClick(position: Int) {
                            viewModel.curCartId = cartList[position].id
                            mainActivity.pushFragments(TabTag.TAB_CART, CartDetailFragment(), true)
                        }
                        override fun onRemoveButtonClick(position: Int) {
                            showRemoveCartDialog(cartList[position].id, position)
                        }
                    })
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "바구니가 없어요!", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showRemoveCartDialog(cartId: Long, position: Int) {
        val title = "해당 장바구니를 지울까요?"
        val body = "다시 되돌릴 수 없어요."
        val confirmText = "지울게요!"
        val cancelText = "잠시만요!"

        val removeCartDialog = SimpleDialog(
            title = title,
            body = body,
            confirmButtonText = confirmText,
            cancelButtonText = cancelText
        )

        removeCartDialog.show(parentFragmentManager, "RemoveCartDialog: SimpleDialog")
        removeCartDialog.setButtonClickListener(object : SimpleDialog.OnButtonClickListener{
            override fun onConfirmButtonClicked() {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.removeCart(context, cartId)

                    withContext(Dispatchers.Main) {
                        cartList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                        adapter.notifyItemRangeChanged(position,cartList.size);


                        removeCartDialog.dismiss()
                    }

                }
            }
            override fun onCancelButtonClicked() {
                removeCartDialog.dismiss()
            }
        })
    }
}