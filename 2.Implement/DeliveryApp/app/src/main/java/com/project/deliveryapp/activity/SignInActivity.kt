package com.project.deliveryapp.activity

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.project.deliveryapp.data.SignInInformation
import com.project.deliveryapp.data.UserData
import com.project.deliveryapp.databinding.ActivitySignInBinding
import com.project.deliveryapp.settings.SingletonObject
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.primaryConstructor


class SignInActivity : AppCompatActivity() {

    private var viewBinding: ActivitySignInBinding? = null
    private val binding get() = viewBinding!!

    private lateinit var idText: EditText
    private lateinit var pwText: EditText
    private lateinit var signUpBtn: Button
    private lateinit var signInBtn: Button
    private lateinit var checkSignIn: CheckBox



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //allocation UI
        idText = binding.idText
        pwText = binding.pwText
        signInBtn = binding.signInBtn
        signUpBtn = binding.signUpBtn
        checkSignIn = binding.checkbox

        //Get saved Data
        SingletonObject.getUserData()

        signInBtn.setOnClickListener {

        }
        signUpBtn.setOnClickListener {

        }
        checkSignIn.setOnCheckedChangeListener { view, isChecked ->
            val result = when(isChecked) {
                true -> 1
                false -> 2
            }
        }

    }


}