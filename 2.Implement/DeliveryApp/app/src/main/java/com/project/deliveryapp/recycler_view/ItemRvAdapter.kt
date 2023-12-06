package com.project.deliveryapp.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.deliveryapp.data.Stock
import com.project.deliveryapp.databinding.StockItemBinding

class ItemRvAdapter(private val dataSet:Array<Stock>): RecyclerView.Adapter<ItemRvAdapter.ViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(val binding: StockItemBinding): RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.itemImage
        val nameText: TextView = binding.nameText
        val weightText: TextView = binding.weightText
        val manufacturerText: TextView = binding.manufacturerText
        val priceText: TextView = binding.priceText

        init {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRvAdapter.ViewHolder {
        val binding = StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        with(holder) {
            image.setImageBitmap(item.imageBitmap)
            nameText.text = item.name
            manufacturerText.text = item.manufacturer
            weightText.text = item.weight.toString()
            priceText.text = item.price.toString()

            binding.root.setOnClickListener{
                itemClickListener?.onClick(position)
            }
        }

    }

    override fun getItemCount(): Int = dataSet.size


    interface OnItemClickListener {
        fun onClick(position: Int)
    }


}