package com.example.aroundegypt.experience_response

data class OpeningHours(
    val friday: List<String>,
    val monday: List<String>,
    val saturday: List<String>,
    val sunday: List<String>,
    val thursday: List<String>,
    val tuesday: List<String>,
    val wednesday: List<String>
)