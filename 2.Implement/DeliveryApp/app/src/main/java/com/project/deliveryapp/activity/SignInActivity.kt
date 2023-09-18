package com.project.deliveryapp.activity

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.project.deliveryapp.R
import com.project.deliveryapp.databinding.ActivitySignInBinding
import kotlin.math.sign

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

        idText = binding.idText
        pwText = binding.pwText
        signInBtn = binding.signInBtn
        signUpBtn = binding.signUpBtn
        checkSignIn = binding.checkbox

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