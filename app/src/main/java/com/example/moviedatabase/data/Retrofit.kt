package com.example.moviedatabase.data

import com.example.moviedatabase.ui.MovieListActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(MovieListActivity.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}