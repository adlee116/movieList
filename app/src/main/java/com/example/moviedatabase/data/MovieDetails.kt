package com.example.moviedatabase.data

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("id")
    var id: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("vote_average")
    var voteAverage: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("overview")
    var overview: String
)