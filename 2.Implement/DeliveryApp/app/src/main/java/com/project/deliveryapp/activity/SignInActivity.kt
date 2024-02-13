package com.project.deliveryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.project.deliveryapp.data.User
import com.project.deliveryapp.data.enum.Gender
import com.project.deliveryapp.databinding.ActivitySignInBinding
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.user.LoginDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.user.LoginResponseDto
import com.project.deliveryapp.settings.SingletonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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

        communicator = ServerCommunicator()

        idText = binding.idText
        pwText = binding.pwText
        signInBtn = binding.signInBtn
        signUpBtn = binding.signUpBtn
        checkSignIn = binding.checkbox

        //------------------------------------------------------------------------------------------------

        signInBtn.setOnClickListener {
            val loginId = idText.text.toString()
            val loginPwd = pwText.text.toString()
            val loginDto = LoginDto(loginId, loginPwd)

            LoadingDialog.show(this@SignInActivity)
            CoroutineScope(Dispatchers.IO).launch {
                val response = runCatching{ communicator.tryLogin(loginDto) }
                    .onFailure {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@SignInActivity, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                            LoadingDialog.dismiss()
                        }
                        return@launch
                    }.getOrNull()!!


                if(response.result) {
                    val obj = response.response as LoginResponseDto
                    val user = User(
                        obj.id,
                        obj.name,
                        obj.nickname,
                        obj.loginId,
                        obj.loginPwd,
                        obj.phoneNumber,
                        if(obj.gender=="Male") Gender.Male else Gender.Female,
                        obj.address
                    )
                    SingletonObject.setUserData(user)
                    if(checkSignIn.isChecked) SingletonObject.saveUserDataInRoom(applicationContext)

                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val error = response.response as ErrorResponseDto
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignInActivity, error.comment, Toast.LENGTH_SHORT).show()
                    }
                }

                LoadingDialog.dismiss()
            }
        }
        signUpBtn.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }





}