package com.project.deliveryapp.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.project.deliveryapp.R
import com.project.deliveryapp.data.Review
import com.project.deliveryapp.data.dto.MarketReviewDto
import com.project.deliveryapp.databinding.RecentMarketItemBinding
import com.project.deliveryapp.databinding.ReviewItemBinding
import com.project.deliveryapp.databinding.StockItemBinding


class ReviewRvAdapter(private val dataSet:List<MarketReviewDto>): RecyclerView.Adapter<ReviewRvAdapter.ViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(val binding: ReviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        val userName: TextView = binding.userName
        val scoreText: TextView = binding.scoreText
        val reviewText: TextView = binding.reviewText
        val reportBtn: Button = binding.reportBtn
        val modifyBtn: Button = binding.modifyBtn


        init {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.userName.text = item.nickname
        holder.reviewText.text = item.comment
        holder.scoreText.text = "${item.score} / 5"


        holder.binding.root.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }

        holder.reportBtn.setOnClickListener {
            itemClickListener?.onReportClick(position)
        }
        holder.modifyBtn.setOnClickListener {
            itemClickListener?.onDeleteClick(position)
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onReportClick(position: Int)
        fun onDeleteClick(position: Int)
    }

}