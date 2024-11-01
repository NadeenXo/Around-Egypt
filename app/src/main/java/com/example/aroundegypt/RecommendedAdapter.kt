package com.example.aroundegypt

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.aroundegypt.databinding.ItemRecommendBinding
import com.example.aroundegypt.experience_response.Data

class RecommendedAdapter(
    private var data: List<Data>,
    private val listener: RecommendedListener
) : RecyclerView.Adapter<RecommendedAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemRecommendBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Data>) {
        Log.d("RecommendedAdapter", "Updating data size: ${newData.size}")
        data = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recommended = data[position]

        holder.binding.tvTitle.text = recommended.title
        holder.binding.tvLikesNum.text = recommended.likes_no.toString()
        holder.binding.tvViewsNum.text = recommended.views_no.toString()

        Glide.with(holder.binding.root.context)
            .load(recommended.cover_photo)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(holder.binding.ivCover)

        holder.binding.root.setOnClickListener {
            listener.onRecommendedClick(recommended.id)
        }
    }
}

interface RecommendedListener {
    fun onRecommendedClick(id: String)
}