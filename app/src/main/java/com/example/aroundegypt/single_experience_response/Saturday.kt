package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class Saturday (

  @SerializedName("day"  ) var day  : String? = null,
  @SerializedName("time" ) var time : String? = null

)