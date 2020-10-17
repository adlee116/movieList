package com.example.moviedatabase.data

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("popular")
    fun fetchPopularMovies(@Query("api_key") apiKey: String): Call<JsonObject>

    @GET("popular")
    fun fetchPopularMoviePage(
        @Query("api_key") apiKey: String,
        @Query("page") page: String
    ): Call<JsonObject>

}
