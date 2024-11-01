package com.example.aroundegypt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aroundegypt.databinding.ItemRecentBinding
import com.example.aroundegypt.experience_response.Data

class RecentAdapter(
    private var data: List<Data>,
    private val listener: RecentListener
) : RecyclerView.Adapter<RecentAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemRecentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Data>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val experience = data[position]

        holder.binding.tvDesc.text = experience.title
        holder.binding.tvLikesNo.text = experience.likes_no.toString()
        holder.binding.tvViewsNo.text = experience.views_no.toString()

        Glide.with(holder.binding.root.context)
            .load(experience.cover_photo)
            .into(holder.binding.ivCover)

        holder.binding.root.setOnClickListener {
            listener.onExperienceClick(experience.id)
        }
    }
}

interface RecentListener {
    fun onExperienceClick(id: String)
}