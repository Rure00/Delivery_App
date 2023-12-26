package com.project.deliveryapp.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.project.deliveryapp.data.Review
import com.project.deliveryapp.databinding.MyReviewItemBinding
import com.project.deliveryapp.databinding.ReviewItemBinding

class MyReviewRvAdapter(private val dataSet:ArrayList<Review>): RecyclerView.Adapter<MyReviewRvAdapter.ViewHolder>() {
    var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(val binding: MyReviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        val marketName: TextView = binding.marketName
        val scoreText: TextView = binding.scoreText
        val reviewText: TextView = binding.reviewText
        val removeButton: Button = binding.removeBtn
        val modifyBtn: Button = binding.modifyBtn
        val reviewDate: TextView = binding.reviewDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MyReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        with(holder) {
            marketName.text = item.marketName
            reviewDate.text = item.date.toString()
            reviewText.text = item.comment
            scoreText.text = "${item.score} / 5"

            removeButton.setOnClickListener {
                itemClickListener?.onRemoveClick(position)
            }
            modifyBtn.setOnClickListener {
                itemClickListener?.onModifyClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface OnItemClickListener {
        fun onRemoveClick(position: Int)
        fun onModifyClick(position: Int)
    }
    fun setOnClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}