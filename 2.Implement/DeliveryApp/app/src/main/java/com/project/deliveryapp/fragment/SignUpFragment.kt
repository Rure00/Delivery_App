package com.project.deliveryapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.deliveryapp.R
import com.project.deliveryapp.databinding.FragmentSignUpBinding
import com.project.deliveryapp.fragment.loading.LoadingFragmentManager
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.SignUpDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Random


class SignUpFragment : Fragment() {
    private var viewBinding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = viewBinding!!

    //Variable for phone Certification
    private var randNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.confirm.setOnClickListener {
            val pwd = binding.pwd.text.toString()
            val pwdConfirm = binding.confirm.toString()

            if(binding.confirmButton.text == "취소") {
                binding.pwd.isFocusable = true
                binding.confirm.isFocusable = true
                binding.confirmButton.text = "확인"
                return@setOnClickListener
            }

            if(pwd == pwdConfirm) {
                binding.pwd.isFocusable = false
                binding.confirm.isFocusable = false
                binding.confirmButton.text = "취소"
            } else {
                Toast.makeText(requireContext(), "비밀 번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.requestCertification.setOnClickListener {
            val communicator = ServerCommunicator()
            val phoneNumber = binding.phoneNumber.text.toString()
            val loadingFragmentManager = LoadingFragmentManager()
            loadingFragmentManager.circleLoading(childFragmentManager, binding.loadingContainer.id)

            CoroutineScope(Dispatchers.IO).launch {
                randNum = Random().nextInt(100000 - 10000) + 10000
                communicator.requestCertification(randNum, phoneNumber)
            }
        }

        binding.numberConfirmButton.setOnClickListener {
            if(binding.numberConfirm.text.toString() == randNum.toString()) {
                binding.requestCertification.isFocusable = false
                binding.phoneNumber.isFocusable = false
                binding.numberConfirmButton.isFocusable = false
                binding.numberConfirm.isFocusable = false

            } else {
                Toast.makeText(requireContext(), "인증번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUpButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val communicator = ServerCommunicator()
                //TODO:
                val signUpDto = SignUpDto(
                    ""
                )
                communicator.trySignUp(signUpDto)
            }
            val manager = requireActivity().supportFragmentManager
            manager.beginTransaction().remove(this@SignUpFragment).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
