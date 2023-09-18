package com.project.deliveryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.deliveryapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private var viewBinding: ActivitySignUpBinding? = null
    private val binding get() = viewBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}