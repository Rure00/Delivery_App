package com.project.deliveryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.databinding.ActivitySignUpBinding
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.fragment.loading.LoadingFragmentManager
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.user.SignUpDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.SignUpResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var communicator: ServerCommunicator

    private var randNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        communicator = ServerCommunicator()

        onBackPressedDispatcher.addCallback {
            finish()
        }

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
                Toast.makeText(this, "비밀 번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.requestCertification.setOnClickListener {
            val communicator = ServerCommunicator()
            val phoneNumber = binding.phoneNumber.text.toString()
            val loadingFragmentManager = LoadingFragmentManager()
            loadingFragmentManager.circleLoading(supportFragmentManager, binding.loadingContainer.id)

            CoroutineScope(Dispatchers.IO).launch {
                randNum = Random().nextInt(100000 - 10000) + 10000
                //TODO: communicator.requestCertification(randNum, phoneNumber)
            }
        }

        binding.numberConfirmButton.setOnClickListener {
            if(binding.numberConfirm.text.toString() == randNum.toString()) {
                binding.requestCertification.isFocusable = false
                binding.phoneNumber.isFocusable = false
                binding.numberConfirmButton.isFocusable = false
                binding.numberConfirm.isFocusable = false

            } else {
                Toast.makeText(this, "인증번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUpButton.setOnClickListener {
            val signUpDto = with(binding) {
                val obj = SignUpDto(
                    name.text.toString(),
                    nickname.text.toString(),
                    id.text.toString(),
                    pwd.text.toString(),
                    phoneNumber.text.toString(),
                    if(maleButton.isChecked) "Male" else "Female",
                    address.text.toString(),
                )

                if(obj.isOkay()) obj
                else {
                    Toast.makeText(this@SignUpActivity, "정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            LoadingDialog.show(this@SignUpActivity)
            CoroutineScope(Dispatchers.IO).launch {

                val response = runCatching{ communicator.trySignUp(signUpDto) }
                    .onFailure {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@SignUpActivity, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                            LoadingDialog.dismiss()
                        }
                        return@launch
                    }.getOrNull()!!

                if(response.result) {
                    val obj = response.response as SignUpResponseDto
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, obj.description, Toast.LENGTH_SHORT).show()
                    }
                    finish()
                } else {
                    val obj = response.response as ErrorResponseDto
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignUpActivity, obj.comment, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            LoadingDialog.dismiss()
        }
    }
}