package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName


data class SingleExperience(

    @SerializedName("meta") var meta: Meta? = Meta(),
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("pagination") var pagination: Nothing? = null

)