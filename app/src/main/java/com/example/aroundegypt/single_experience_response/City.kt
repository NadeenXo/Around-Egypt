package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName

data class City (
  @SerializedName("id"       ) var id      : Int?    = null,
  @SerializedName("name"     ) var name    : String? = null,
  @SerializedName("disable"  ) var disable : String? = null,
  @SerializedName("top_pick" ) var topPick : Int?    = null

)