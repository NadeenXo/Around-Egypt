package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class Meta (

  @SerializedName("code"   ) var code   : Int?              = null,
  @SerializedName("errors" ) var errors : ArrayList<String> = arrayListOf()

)