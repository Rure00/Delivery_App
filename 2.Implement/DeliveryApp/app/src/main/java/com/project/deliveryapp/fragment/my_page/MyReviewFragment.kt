package com.project.deliveryapp.fragment.my_page

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
import com.project.deliveryapp.data.Review
import com.project.deliveryapp.databinding.FragmentMyReviewBinding
import com.project.deliveryapp.dialog.SimpleDialog
import com.project.deliveryapp.recycler_view.MyReviewRvAdapter
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyReviewFragment : Fragment() {
    private var _binding: FragmentMyReviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: MyReviewRvAdapter
    private lateinit var myReviewList: ArrayList<Review>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mainActivity.onBackPressedDispatcher.addCallback() {
            mainActivity.popFragments()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentMyReviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            myReviewList = viewModel.getMyReviews()

            if(myReviewList.isNotEmpty()) {
                adapter = MyReviewRvAdapter(myReviewList)
                adapter.notifyItemRangeInserted(0, myReviewList.size)

                val recyclerView = binding.recyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                adapter.setOnClickListener(object : MyReviewRvAdapter.OnItemClickListener {
                    override fun onRemoveClick(position: Int) {
                        showRemoveReviewDialog(myReviewList[position], position)
                    }

                    override fun onModifyClick(position: Int) {
                        Toast.makeText(context, "Not Yet Implemented. Review Modifying Fragment", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun showRemoveReviewDialog(review: Review, position: Int) {
        val title = "해당 리뷰를 지울까요?"
        val body = "다시 되돌릴 수 없어요."
        val confirmText = "지울게요!"
        val cancelText = "잠시만요!"

        val dialog = SimpleDialog(
            title = title,
            body = body,
            confirmButtonText = confirmText,
            cancelButtonText = cancelText
        )

        dialog.setButtonClickListener(object : SimpleDialog.OnButtonClickListener{
            override fun onConfirmButtonClicked() {
                viewModel.removeReview(review)
                myReviewList.removeAt(position)

                adapter.notifyItemRemoved(position)

                dialog.dismiss()
            }

            override fun onCancelButtonClicked() {
                dialog.dismiss()
            }

        })

        dialog.show(parentFragmentManager, "RemoveDialog")
    }
}