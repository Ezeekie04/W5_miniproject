package com.example.w5.api

import com.example.w5.model.DogResponse
import retrofit2.Call
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    fun getRandomDog(): Call<DogResponse>
}