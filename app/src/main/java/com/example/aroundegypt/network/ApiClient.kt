package com.example.aroundegypt.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "https://aroundegypt.34ml.com/"

    fun getInstance(): ApiService {
        return retrofitInstance
    }

    private val retrofitInstance: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}
