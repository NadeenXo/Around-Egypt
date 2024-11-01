package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class Era (

  @SerializedName("id"         ) var id        : String? = null,
  @SerializedName("value"      ) var value     : String? = null,
  @SerializedName("created_at" ) var createdAt : String? = null,
  @SerializedName("updated_at" ) var updatedAt : String? = null

)