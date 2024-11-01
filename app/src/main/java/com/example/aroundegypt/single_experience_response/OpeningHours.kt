package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class OpeningHours (

  @SerializedName("sunday"    ) var sunday    : ArrayList<String> = arrayListOf(),
  @SerializedName("monday"    ) var monday    : ArrayList<String> = arrayListOf(),
  @SerializedName("tuesday"   ) var tuesday   : ArrayList<String> = arrayListOf(),
  @SerializedName("wednesday" ) var wednesday : ArrayList<String> = arrayListOf(),
  @SerializedName("thursday"  ) var thursday  : ArrayList<String> = arrayListOf(),
  @SerializedName("friday"    ) var friday    : ArrayList<String> = arrayListOf(),
  @SerializedName("saturday"  ) var saturday  : ArrayList<String> = arrayListOf()

)