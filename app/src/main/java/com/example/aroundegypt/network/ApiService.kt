package com.example.aroundegypt.network

import com.example.aroundegypt.experience_response.ExperiencesResponse
import com.example.aroundegypt.single_experience_response.SingleExperience
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // GET Recommended experiences
    @GET("api/v2/experiences?filter[recommended]=true")
    suspend fun getRecommendedExperiences(): Response<ExperiencesResponse>

    // GET Recent experiences
    @GET("api/v2/experiences")
    suspend fun getRecentExperiences(): Response<ExperiencesResponse>


    // GET Search experiences by title
    @GET("api/v2/experiences")
    suspend fun searchExperiences(
        @Query("filter[title]") searchText: String
    ): Response<ExperiencesResponse>

    // GET Single experience by ID
    @GET("api/v2/experiences/{id}")
    suspend fun getSingleExperience(@Path("id") id: String): Response<SingleExperience>

    // POST Like an experience
    @POST("api/v2/experiences/{id}/like")
    suspend fun likeExperience(
        @Path("id") id: String
    ): Response<Unit>
//    }
//
}

