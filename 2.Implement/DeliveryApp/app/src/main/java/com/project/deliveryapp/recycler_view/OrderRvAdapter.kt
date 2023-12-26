package com.project.deliveryapp.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.deliveryapp.data.Order
import com.project.deliveryapp.databinding.OrderItemBinding

class OrderRvAdapter(private val dataSet:ArrayList<Order>): RecyclerView.Adapter<OrderRvAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(val binding: OrderItemBinding): RecyclerView.ViewHolder(binding.root) {
        val nameText: TextView = binding.marketName
        val priceText: TextView = binding.priceText
        val checkButton: Button = binding.checkButton
        val orderState: TextView = binding.orderState
        val orderDate: TextView = binding.orderDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        //TODO: order 데이터 클래스 구현하고 adapter 완성하기
        with(holder) {
            nameText.text = item.marketName
            orderDate.text = item.date.toLocalDate().toString()
            orderState.text = item.state.toString()
            priceText.text = item.cost.toString()

            checkButton.setOnClickListener{
                itemClickListener.onCheckButtonClick(position)
            }
        }
    }
    override fun getItemCount(): Int = dataSet.size

    interface OnItemClickListener {
        fun onCheckButtonClick(position: Int)
    }
    fun setOnClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}