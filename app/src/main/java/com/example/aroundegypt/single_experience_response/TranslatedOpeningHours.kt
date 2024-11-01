package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class TranslatedOpeningHours (

  @SerializedName("sunday"    ) var sunday    : Sunday?    = Sunday(),
  @SerializedName("monday"    ) var monday    : Monday?    = Monday(),
  @SerializedName("tuesday"   ) var tuesday   : Tuesday?   = Tuesday(),
  @SerializedName("wednesday" ) var wednesday : Wednesday? = Wednesday(),
  @SerializedName("thursday"  ) var thursday  : Thursday?  = Thursday(),
  @SerializedName("friday"    ) var friday    : Friday?    = Friday(),
  @SerializedName("saturday"  ) var saturday  : Saturday?  = Saturday()

)