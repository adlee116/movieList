package com.example.moviedatabase.viewModel

import androidx.lifecycle.ViewModel
import com.example.moviedatabase.data.MovieDetails
import com.example.moviedatabase.ui.MovieItemForList
import com.example.moviedatabase.ui.MovieListActivity
import com.example.moviedatabase.ui.MovieListItems
import com.example.moviedatabase.ui.NextPageItemForList
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject


//TODO requires testing
//TODO Ideally the viewModel would have use cases injected inside Class Params here
open class MovieListViewModel: ViewModel() {


    //TODO due to package structure, these methods are not private so they are accessible for testing.
    fun createMovieListItems(currentPage: Int, totalPages: Int, movieList: List<MovieDetails>): List<MovieListItems> {
        val itemList = mutableListOf<MovieListItems>()
        movieList.forEach {
            itemList.add(convertMovieToListItemsForAdapter(it))
        }
        itemList.add(
            NextPageItemForList(
                previousAvailable = currentPage > 1,
                nextAvailable = currentPage < totalPages
            )
        )
        return itemList
    }

    fun convertMovieToListItemsForAdapter(movieDetail: MovieDetails): MovieItemForList {
        return MovieItemForList(
            id = movieDetail.id,
            posterPath = updateImageUri(movieDetail.posterPath),
            originalTitle = movieDetail.originalTitle,
            voteAverage = movieDetail.voteAverage,
            releaseDate = movieDetail.releaseDate,
            overview = movieDetail.overview
        )
    }

    fun updateImageUri(incompletedPostPath: String): String {
        val apiKey = "?api_key=f37fcd6242a1b823af26107652db1803"
        return (MovieListActivity.BASE_IMAGE_URL + incompletedPostPath + apiKey)
    }

    fun getCurrentPageAndTotalPages(json: JsonObject): Pair<Int, Int> {
        var defaultCurrentPage = 0
        var totalPages = 1

        if (json.has("page")) {
            defaultCurrentPage = json.get("page").toString().toInt()
        }
        if (json.has("total_pages")) {
            totalPages = json.get("total_pages").toString().toInt()
        }
        return Pair(defaultCurrentPage, totalPages)
    }

    fun convertJsonToMovieList(json: JsonObject?): List<MovieDetails>? {
        var results: JsonArray? = null
        var convertedResults: List<MovieDetails>? = null
        json?.let {
            if (it.has("results")) {
                results = it.get("results").asJsonArray
            }
        }
        results?.let {
            val gson: Gson = GsonBuilder().create()
            convertedResults = gson.fromJson(results, Array<MovieDetails>::class.java).toList()
        }
        return convertedResults
    }

}