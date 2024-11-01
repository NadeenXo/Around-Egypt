package com.example.aroundegypt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.aroundegypt.databinding.ActivityExperienceBinding
import com.example.aroundegypt.network.APIClient
import com.example.aroundegypt.network.ApiService
import com.example.aroundegypt.single_experience_response.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExperienceActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityExperienceBinding
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExperienceBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        id = intent.getStringExtra(HomeFragment.EXPERIENCE_ID).toString()
        val service = APIClient.getInstance()
        fetchData(service)
    }

    private fun fetchData(service: ApiService) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getSingleExperience(id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val experienceData = response.body()?.data
                        if (experienceData != null) {
                            updateUI(experienceData)
                        } else {
                            Toast.makeText(
                                this@ExperienceActivity,
                                "No data available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@ExperienceActivity,
                            "Failed to load data: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ExperienceActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun updateUI(experience: Data?) {
        _binding.tvTitle.text = experience?.title
        _binding.tvDesc.text = experience?.description
        _binding.tvLikesNum.text = experience?.likesNo.toString()
        _binding.tvViewsNum.text = experience?.viewsNo.toString()
        _binding.tvSubtitle.text = experience?.address

        Glide.with(this)
            .load(experience?.coverPhoto)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(_binding.ivCover)
    }
}