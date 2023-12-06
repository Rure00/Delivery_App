package com.project.deliveryapp.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.deliveryapp.data.Cart
import com.project.deliveryapp.databinding.CartItemBinding

class CartRvAdapter(private val dataSet:ArrayList<Cart>): RecyclerView.Adapter<CartRvAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root) {
        val nameText: TextView = binding.marketName
        val priceText: TextView = binding.priceText
        val checkButton: Button = binding.checkButton
        val removeButton: Button = binding.removeButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRvAdapter.ViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        with(holder) {
            nameText.text = item.marketName
            priceText.text = item.expense.toString()

            checkButton.setOnClickListener{
                itemClickListener.onCheckButtonClick(position)
            }
            removeButton.setOnClickListener {
                itemClickListener.onRemoveButtonClick(position)
            }
        }


    }

    override fun getItemCount(): Int = dataSet.size


    interface OnItemClickListener {
        fun onCheckButtonClick(position: Int)
        fun onRemoveButtonClick(position: Int)
    }
    fun setOnClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}