package com.project.deliveryapp.fragment.sign_up

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.project.deliveryapp.R
import com.project.deliveryapp.databinding.FragmentMarketSignUpBinding
import com.project.deliveryapp.naver.NaverGeoCoding
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.market.MarketSignUpDto
import com.project.deliveryapp.retrofit.dto.request.user.SignUpDto
import com.project.deliveryapp.view_model.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Random


class MarketSignUpFragment : Fragment() {
    private var _binding: FragmentMarketSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var geoCoding: NaverGeoCoding
    private lateinit var viewModel: SignUpViewModel
    private lateinit var context: Context

    private lateinit var listener: SignUpClickListener
    private var pwdConfirm: Boolean = false
    private var certicated: Boolean = false
    private var addressCheck: Boolean = true       //TODO: after Making Address checking Algorithm, use it.


    private var randNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        geoCoding = NaverGeoCoding()
        viewModel = ViewModelProvider(requireActivity())[SignUpViewModel::class.java]
        context = requireContext()


        listener = object: SignUpClickListener{
            override fun callDto(): MarketSignUpDto? {
                if(!pwdConfirm && !certicated && !addressCheck) {
                    return null
                }

                var marketSignUpDto: MarketSignUpDto? = null

                CoroutineScope(Dispatchers.IO).launch {
                    val location = runCatching {
                        geoCoding.getLatLng(binding.address.text.toString())
                    }.onFailure {
                        Log.e("GeoCoding", "Failed to Find LntLag.")
                        return@launch
                    }.getOrNull()!!

                    with(binding) {
                        val obj = MarketSignUpDto(
                            name.text.toString(),
                            id.text.toString(),
                            pwd.text.toString(),
                            phoneNumber.text.toString(),
                            location.latitude,
                            location.longitude,
                            descriptionText.text.toString(),
                            address.text.toString(),
                        )

                        if(obj.isOkay()) marketSignUpDto = obj
                        else {
                            Toast.makeText(context, "정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
                            return@with
                        }
                    }
                }

                return marketSignUpDto
            }
        }

        viewModel.setMarketInterface(listener)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMarketSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //비밀번호 확인
        binding.confirm.setOnClickListener {
            val pwdText = binding.pwd.text.toString()
            val pwdConfirmText = binding.confirm.toString()

            if(pwdText == pwdConfirmText) {
                binding.pwd.isFocusable = false
                binding.confirm.isFocusable = false
                binding.confirmButton.text = "취소"

                Toast.makeText(context, "확인.", Toast.LENGTH_SHORT).show()
                pwdConfirm = true
            } else {
                Toast.makeText(context, "비밀 번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        //인증 요청
        binding.requestCertification.setOnClickListener {
            val communicator = ServerCommunicator()
            val phoneNumber = binding.phoneNumber.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                randNum = Random().nextInt(100000 - 10000) + 10000
                //TODO: communicator.requestCertification(randNum, phoneNumber)
            }
        }
        //인증하기
        binding.numberConfirmButton.setOnClickListener {
            if(binding.numberConfirm.text.toString() == randNum.toString()) {
                binding.requestCertification.isFocusable = false
                binding.phoneNumber.isFocusable = false
                binding.numberConfirmButton.isFocusable = false
                binding.numberConfirm.isFocusable = false

                certicated = true

            } else {
                Toast.makeText(context, "인증번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    interface SignUpClickListener {
        fun callDto(): MarketSignUpDto?
    }

}