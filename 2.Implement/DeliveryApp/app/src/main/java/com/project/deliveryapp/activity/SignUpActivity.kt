package com.project.deliveryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.databinding.ActivitySignUpBinding
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.fragment.sign_up.MarketSignUpFragment
import com.project.deliveryapp.fragment.sign_up.UserSignUpFragment
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.user.SignUpDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.SignUpResponseDto
import com.project.deliveryapp.view_model.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var communicator: ServerCommunicator

    private lateinit var viewModel: SignUpViewModel
    private var listener: SignUpBtnListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        communicator = ServerCommunicator()

        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        onBackPressedDispatcher.addCallback {
            finish()
        }

        viewModel.setSignUpBtnListener(this)

        binding.customerButton.setOnClickListener {
            viewModel.signUpTarget = SignUpViewModel.SignUpTarget.USER
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, UserSignUpFragment()).commit()
        }
        binding.marketButton.setOnClickListener {
            viewModel.signUpTarget = SignUpViewModel.SignUpTarget.MARKET
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, MarketSignUpFragment()).commit()
        }

        binding.signUpButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val flag = listener!!.onClick()
                if(flag) {
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    interface SignUpBtnListener {
        suspend fun onClick(): Boolean
    }
    fun setSignUpBtnListener(listener: SignUpBtnListener) {
        this.listener = listener
    }
}