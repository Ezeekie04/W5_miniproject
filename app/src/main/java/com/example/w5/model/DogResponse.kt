package com.example.w5.model


import com.squareup.moshi.Json

// Model sesuai struktur JSON dari Dog API
data class DogResponse(
    @field:Json(name = "message") val imageUrl: String,
    val status: String
)
