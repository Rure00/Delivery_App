package com.project.deliveryapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.deliveryapp.R
import com.project.deliveryapp.activity.MainActivity
import com.project.deliveryapp.activity.TabTag
import com.project.deliveryapp.databinding.FragmentMyPageBinding
import com.project.deliveryapp.view_model.MainViewModel

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        requireActivity().onBackPressedDispatcher.addCallback(this@MyPageFragment) {
            mainActivity.toFindTab()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            myOrderButton.setOnClickListener {
                mainActivity.pushFragments(TabTag.TAB_MY_PAGE, MyOrderFragment(), true)
            }
            myReviewButton.setOnClickListener {
                mainActivity.pushFragments(TabTag.TAB_MY_PAGE, MyReviewFragment(), true)
            }
            favorite.setOnClickListener {
                Toast.makeText(context, "추후에 제공될 서비스입니다.", Toast.LENGTH_SHORT).show()
            }
            commonQuestions.setOnClickListener {
                Toast.makeText(context, "추후에 제공될 서비스입니다.", Toast.LENGTH_SHORT).show()
            }
            inquiry.setOnClickListener {
                Toast.makeText(context, "추후에 제공될 서비스입니다.", Toast.LENGTH_SHORT).show()
            }
            serviceTeam.setOnClickListener {
                Toast.makeText(context, "추후에 제공될 서비스입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}