package com.example.aroundegypt

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.aroundegypt.databinding.ItemRecommendBinding
import com.example.aroundegypt.experience_response.Data
import com.example.dto.FavDAO
import com.example.dto.FavDataBase
import com.example.dto.FavouriteTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecommendedAdapter(
    private var data: List<Data>,
    private val listener: RecommendedListener
) : RecyclerView.Adapter<RecommendedAdapter.MyViewHolder>() {

    private lateinit var favDao: FavDAO

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

        favDao = FavDataBase.getInstance(holder.itemView.context).favDao()

        CoroutineScope(Dispatchers.IO).launch {
            val isFavorite = favDao.getFavById(recommended.id).isNotEmpty()

            // Update UI on the main thread
            withContext(Dispatchers.Main) {
                if (isFavorite) {
                    like(holder)
                }
//                else {
//                    dislike(holder)
//                }
            }
        }

        holder.binding.root.setOnClickListener {
            listener.onRecommendedClick(recommended.id)
        }
        holder.binding.ivLike.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {
                    addToFav(recommended)
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

    private fun dislike(holder: MyViewHolder) {
        holder.binding.ivLike.setImageDrawable(
            ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.ic_unlike
            )
        )
    }
}

interface RecommendedListener {
    fun onRecommendedClick(id: String)
}