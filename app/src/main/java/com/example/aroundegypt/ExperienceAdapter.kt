package com.example.aroundegypt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aroundegypt.databinding.ItemRecommendBinding
import com.example.aroundegypt.experience_response.Data

class ExperienceAdapter(
    private var data: List<Data>,
    private val listener: ExperienceListener
) : RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemRecommendBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Data>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val experience = data[position]

        holder.binding.tvTitle.text = experience.title
        holder.binding.tvLikesNum.text = experience.likes_no.toString()
        holder.binding.tvViewsNum.text = experience.views_no.toString()

        Glide.with(holder.binding.root.context)
            .load(experience.cover_photo)
            .into(holder.binding.ivCover)

        holder.binding.root.setOnClickListener {
            listener.onExperienceClick(holder.binding)
        }
    }
}

interface ExperienceListener {
    fun onExperienceClick(experience: ItemRecommendBinding)
}