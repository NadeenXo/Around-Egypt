package com.example.aroundegypt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.aroundegypt.databinding.ItemRecentBinding
import com.example.aroundegypt.experience_response.Data
import com.example.dto.FavDAO
import com.example.dto.FavDataBase
import com.example.dto.FavouriteTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecentAdapter(
    private var data: List<Data>,
    private val listener: RecentListener
) : RecyclerView.Adapter<RecentAdapter.MyViewHolder>() {
    private lateinit var favDao: FavDAO

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
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(holder.binding.ivCover)

        holder.binding.root.setOnClickListener {
            listener.onExperienceClick(experience.id)
        }
        holder.binding.ivLike.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {
                    addToFav(experience)
                    like(holder)
                }
            }
        }
        favDao = FavDataBase.getInstance(holder.itemView.context).favDao()

        CoroutineScope(Dispatchers.IO).launch {
            val isFavorite = favDao.getFavById(experience.id).isNotEmpty()

            // Update UI on the main thread
            withContext(Dispatchers.Main) {
                if (isFavorite) {
                    like(holder)
                }

            }
        }
    }

    private suspend fun addToFav(recommended: Data): List<Long> {
        val newFav = FavouriteTable(
            recommended.id,
            recommended.title,
            recommended.address,
            recommended.description,
            recommended.cover_photo
        )

        return favDao.insert(newFav)
    }

    private fun like(holder: MyViewHolder) {
        holder.binding.ivLike.setImageDrawable(
            ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.ic_like
            )
        )
    }
}

interface RecentListener {
    fun onExperienceClick(id: String)
}