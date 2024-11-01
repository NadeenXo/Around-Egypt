package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class GmapLocation (

  @SerializedName("type"        ) var type        : String?           = null,
  @SerializedName("coordinates" ) var coordinates : ArrayList<Double> = arrayListOf()

)