package com.project.deliveryapp.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.deliveryapp.R
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.databinding.FragmentRecentMarketBinding
import com.project.deliveryapp.databinding.RecentMarketItemBinding

class RecentMarketRvAdapter(private val dataSet:List<MarketData>): RecyclerView.Adapter<RecentMarketRvAdapter.ViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(val binding: RecentMarketItemBinding): RecyclerView.ViewHolder(binding.root) {
        val nameText: TextView = binding.marketName
        val scoreText: TextView = binding.marketScore
        val addressText: TextView = binding.marketAddress
        val descriptionText: TextView = binding.marketDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecentMarketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = dataSet[position].name
        holder.scoreText.text = "${dataSet[position].score} / 5"
        holder.addressText.text = dataSet[position].address
        holder.descriptionText.text = dataSet[position].description

        holder.binding.root.setOnClickListener{
            itemClickListener?.onClick(position)
        }
    }

    override fun getItemCount() = dataSet.size


    interface OnItemClickListener {
        fun onClick(position: Int)
    }


}