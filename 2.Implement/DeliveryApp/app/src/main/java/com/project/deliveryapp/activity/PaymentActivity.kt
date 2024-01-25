package com.project.deliveryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.deliveryapp.R
import com.project.deliveryapp.databinding.ActivityMainBinding
import com.project.deliveryapp.databinding.ActivityPaymentBinding
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.retrofit.ServerCommunicator
import com.project.deliveryapp.retrofit.dto.request.cart.SaveCartDto
import com.project.deliveryapp.retrofit.dto.request.order.GiveOrderDto
import com.project.deliveryapp.retrofit.dto.response.ErrorResponseDto
import com.project.deliveryapp.retrofit.dto.response.cart.SaveCartResponseDto
import com.project.deliveryapp.settings.SingletonObject
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var communicator: ServerCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        communicator = ServerCommunicator()
        val cartId: Long = with(intent.getLongExtra("cartId", -1)) {
            if(this == -1L) throw Exception("No Data is Passed")
            else this
        }

        binding.orderBtn.setOnClickListener {
            val giveOrderDto = GiveOrderDto(SingletonObject.getUserId(), cartId)

            LoadingDialog.show(this@PaymentActivity)
            CoroutineScope(Dispatchers.IO).launch {

                //TODO: 결제 시스템

                val response = runCatching{ communicator.giveOrder(giveOrderDto) }
                    .onFailure {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@PaymentActivity, "나중에 다시 이용해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }.getOrNull()!!

                if(response.result)  onSuccess()
                else onFailure()

                LoadingDialog.dismiss()
            }
        }
    }

    private fun onSuccess() {
        finish()
    }
    private fun onFailure() {
        Toast.makeText(this, "결제에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }

}