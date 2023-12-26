package com.project.deliveryapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.deliveryapp.R
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.data.Review
import com.project.deliveryapp.databinding.FragmentReviewBinding
import com.project.deliveryapp.dialog.SimpleDialog
import com.project.deliveryapp.settings.SingletonObject
import com.project.deliveryapp.view_model.MainViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReviewFragment : Fragment() {
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]


        requireActivity().onBackPressedDispatcher.addCallback(this) {
            getBackPressedDialog().show(parentFragmentManager, "BackPressedDialog: SimpleDialog")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.confirm.setOnClickListener {
            if(binding.content.text.isBlank()) {
                Toast.makeText(context, "내용을 입력해주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            getConfirmDialog().show(parentFragmentManager, "ConfirmDialog: SimpleDialog")
        }
        binding.cancel.setOnClickListener {
            getBackPressedDialog().show(parentFragmentManager, "BackPressedDialog: SimpleDialog")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getBackPressedDialog(): SimpleDialog {
        val title = "취소하고 돌아갈까요?"
        val body = "해당 내용은 저장되지 않아요. 그래도 괜찮을까요?"
        val confirmText = "괜찮아요!"
        val cancelText = "잠시만요!"

        val backPressedDialog = SimpleDialog(
            title, body, confirmText, cancelText
        );

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
    private fun getConfirmDialog(): SimpleDialog {
        val title = resources.getString(R.string.review_dialog_title)
        val body = resources.getString(R.string.review_dialog_body)
        val confirmText = resources.getString(R.string.review_dialog_confirm)
        val cancelText = resources.getString(R.string.review_dialog_cancel)

        val confirmDialog = SimpleDialog(
            title, body, confirmText, cancelText
        )

        confirmDialog.setButtonClickListener(object :SimpleDialog.OnButtonClickListener{
            override fun onConfirmButtonClicked() {
                val userId = SingletonObject.getUserId()
                val marketId = viewModel.getCurMarket().id
                val userNickname = SingletonObject.getUserNickname()
                val marketName = viewModel.getCurMarket().name
                val comment = binding.content.text.toString()
                val score = binding.scoreRating.rating
                val date = LocalDateTime.now()

                val review = Review(
                    0, userId, marketId, userNickname, marketName, comment, score, date
                )
                viewModel.saveReview(review)

                confirmDialog.dismiss()
                mainActivity.popFragments()
            }
            override fun onCancelButtonClicked() {
                confirmDialog.dismiss()
            }
        })

        return confirmDialog
    }


}