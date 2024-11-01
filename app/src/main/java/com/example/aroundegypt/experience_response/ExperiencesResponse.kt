package com.example.aroundegypt.experience_response

data class ExperiencesResponse(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)