package com.project.deliveryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.deliveryapp.R
import com.project.deliveryapp.databinding.ActivityMainBinding
import com.project.deliveryapp.databinding.ActivitySignInBinding
import com.project.deliveryapp.view_model.MainViewModel
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    val TAG: String = "MAIN_ACTIVITY"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()

        with(binding) {
            bnMenu.setupWithNavController(navController)
            bnMenu.selectedItemId = R.id.mainActivity

            bnMenu.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.homeIcon -> {

                    }
                    R.id.cartIcon -> {

                    }
                    R.id.myPageIcon -> {

                    }
                    else -> {

                    }
                }

                true
            }
        }

    }

}
