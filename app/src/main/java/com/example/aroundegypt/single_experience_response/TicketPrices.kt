package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class TicketPrices (

  @SerializedName("type"  ) var type  : String? = null,
  @SerializedName("price" ) var price : Int?    = null

)