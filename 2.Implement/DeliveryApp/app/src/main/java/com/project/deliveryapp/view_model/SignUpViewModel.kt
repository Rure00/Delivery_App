package com.project.deliveryapp.view_model

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.project.deliveryapp.activity.SignUpActivity
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.fragment.sign_up.MarketSignUpFragment
import com.project.deliveryapp.fragment.sign_up.UserSignUpFragment
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.market.MarketSignUpDto
import com.project.deliveryapp.retrofit.dto.request.user.SignUpDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.SignUpResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.sign

class SignUpViewModel: ViewModel() {

    private var communicator: ServerCommunicator = ServerCommunicator()

    private lateinit var signUpDto: SignUpDto
    private lateinit var marketSignUp: MarketSignUpDto


    private var userSignUpListener: UserSignUpFragment.SignUpClickListener? = null
    private var marketSignUpListener: MarketSignUpFragment.SignUpClickListener? = null

    var signUpTarget: SignUpTarget = SignUpTarget.NOT_SELECTED
    enum class SignUpTarget {
        NOT_SELECTED, USER, MARKET
    }


    fun setUserInterface(listener: UserSignUpFragment.SignUpClickListener) {
        this.userSignUpListener = listener
    }
    fun setMarketInterface(listener: MarketSignUpFragment.SignUpClickListener) {
        this.marketSignUpListener = listener
    }

    fun setSignUpBtnListener(activity: SignUpActivity) {
        activity.setSignUpBtnListener(object : SignUpActivity.SignUpBtnListener {
            override suspend fun onClick(): Boolean {
                var flag = false


                withContext(Dispatchers.IO) {
                    val response = runCatching{
                        when(signUpTarget) {
                            SignUpTarget.USER -> {
                                signUpDto = userSignUpListener!!.callDto()!!
                                communicator.trySignUp(signUpDto)
                            }
                            SignUpTarget.MARKET -> {
                                marketSignUp = marketSignUpListener!!.callDto()!!
                                communicator.marketSignUp(marketSignUp)
                            }
                            else -> return@withContext
                        }
                    }.onFailure {
                        Log.e("Error", "Failed to Sign Up.")
                        flag = false
                        return@withContext
                    }.getOrNull()!!

                    flag = if(response.result) {
                        val obj = response.response as SignUpResponseDto
                        Log.d("SignUpResponse", obj.description)
                        true
                    } else {
                        val obj = response.response as ErrorResponseDto
                        Log.e("SignUpResponse", obj.comment)
                        false
                    }
                }

                return flag
            }

        })
    }



}