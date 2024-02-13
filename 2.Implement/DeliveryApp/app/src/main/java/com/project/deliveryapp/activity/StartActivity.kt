package com.project.deliveryapp.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.deliveryapp.databinding.ActivityStartBinding
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.user.LoginDto
import com.project.deliveryapp.retrofit.dto.response.user.LoginResponseDto
import com.project.deliveryapp.settings.PermissionManager
import com.project.deliveryapp.settings.SingletonObject
import com.project.deliveryapp.settings.WindowObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LoadingDialog.show(this@StartActivity)

        WindowObject.setPixels(this)

        requestPermissions(
            PermissionManager.gpsPermission,
            PermissionManager.GPS_REQUEST_CODE
        )

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PermissionManager.GPS_REQUEST_CODE) {
            grantResults.forEach {
                if(it == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(applicationContext, "서비스의 필요한 권한입니다.\n권한에 동의해주세요.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                val roomData = SingletonObject.getSavedUserData(this@StartActivity.applicationContext)
                val isSaved = (roomData != null)

                delay(1500)

                if(isSaved) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val communicator = ServerCommunicator()
                        val loginDto = LoginDto(
                            roomData!!.loginId,
                            roomData.loginPwd
                        )

                        val response = communicator.tryLogin(loginDto).response as LoginResponseDto
                        SingletonObject.setUserData(response.toUser())
                    }


                    val intent = Intent(this@StartActivity.applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@StartActivity.applicationContext, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                LoadingDialog.dismiss()
            }
        }
    }
}