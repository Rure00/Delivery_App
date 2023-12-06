package com.project.deliveryapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.project.deliveryapp.R
import com.project.deliveryapp.data.UserData
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.databinding.ActivitySignInBinding
import com.project.deliveryapp.fragment.SignUpFragment
import com.project.deliveryapp.fragment.loading.CircleLoadingFragment
import com.project.deliveryapp.fragment.loading.LoadingFragmentManager
import com.project.deliveryapp.retrofit.RetrofitClient
import com.project.deliveryapp.retrofit.RetrofitService
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.LoginDto
import com.project.deliveryapp.retrofit.dto.UserDataDto
import com.project.deliveryapp.room.data.MarketDataForRoom
import com.project.deliveryapp.room.data.UserDataForRoom
import com.project.deliveryapp.settings.SingletonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var communicator: ServerCommunicator

    private lateinit var idText: EditText
    private lateinit var pwText: EditText
    private lateinit var signUpBtn: Button
    private lateinit var signInBtn: Button
    private lateinit var checkSignIn: CheckBox



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: server communicator
        //communicator = ServerCommunicator()

        //allocation UI
        idText = binding.idText
        pwText = binding.pwText
        signInBtn = binding.signInBtn
        signUpBtn = binding.signUpBtn
        checkSignIn = binding.checkbox

        //------------------------------------------------------------------------------------------------

        signInBtn.setOnClickListener {
            val loadingFragmentManager = LoadingFragmentManager()
            val loginId = idText.text.toString()
            val loginPwd = pwText.text.toString()
            val loginDto = LoginDto(loginId, loginPwd)

            val ud = UserData(
                0,
                "sung",
                "rure",
                "asn",
                "1234",
                "0101231412414",
                Gender.Male,
                "sadang"
            )

            val md = MarketDataForRoom(
                1, "삼성마트", 3.5f, "275773", "none", 3, "address",
                37.47655, 126.98163
            )

            SingletonObject.setUserData(ud)
            CoroutineScope(Dispatchers.IO).launch {
                SingletonObject.deleteRecentMarket(this@SignInActivity, md)
                SingletonObject.saveRecentMarket(this@SignInActivity, md)

                //TODO: Code for test) after finishing a project, it should be deleted
                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(intent)
                this@SignInActivity.finish()
            }



            /* TODO: server service
            CoroutineScope(Dispatchers.IO).launch {
                val flag = communicator.tryLogin(loginDto)
                if(flag) {
                    if(checkSignIn.isChecked) {
                        val isSuccess = SingletonObject.saveUserDataInRoom(applicationContext)
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(applicationContext, "아이디나 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    loadingFragmentManager.cancelLoading(supportFragmentManager, binding.loadingContainer.id)
                }

            }


            loadingFragmentManager.circleLoading(supportFragmentManager, R.id.loadingContainer)

             */





        }
        signUpBtn.setOnClickListener {
            val signUpFragment = SignUpFragment()
            val transaction = supportFragmentManager.beginTransaction()

            transaction.add(R.id.signUpFragment, signUpFragment)
            transaction.commit()
        }

    }




}