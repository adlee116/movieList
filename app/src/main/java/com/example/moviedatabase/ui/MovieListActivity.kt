package com.example.moviedatabase.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedatabase.R
import com.example.moviedatabase.data.ApiInterface
import com.example.moviedatabase.data.Retrofit
import com.example.moviedatabase.viewModel.MovieListViewModel

import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.movie_list_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//TODO Didn't feel the need for this to be broken in to fragments as the UI would likely not be split on a tablet
class MovieListActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private var currentPage = 0
    private var totalPages = 1

    //TODO have the viewModel injected in to activities / fragments with Koin
    val vm = MovieListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        getMoviesPage(currentPage + 1)
    }

    //TODO would move this to viewModel, but have not extracted server call
    private val onNextOrPreviousClicked = object : NextPageViewHolder.OnNextOrPreviousClicked {
        override fun loadAnotherPage(nextWasClicked: Boolean) {
            val page = if (nextWasClicked) {
                currentPage + 1
            } else {
                currentPage - 1
            }
            getMoviesPage(page)
        }
    }

    private val onItemClicked = object : MovieListViewHolder.OnItemClicked {
        override fun onItemClicked(movieDetails: MovieListItems) {
            movieDetails as MovieItemForList
            MovieDetailsBottomSheet.open(
                fragmentManager = supportFragmentManager,
                movieDetails = movieDetails
            )
        }
    }

    //TODO all to be extracted in to Live Data observable with use cases
    // Use case to be called, update variable state, to drive the UI via Live Data
    private fun getMoviesPage(pageNumber: Int) {
        val apiInterface = Retrofit().retrofit().create(ApiInterface::class.java)
        val call = apiInterface.fetchPopularMoviePage(API_KEY, (pageNumber).toString())

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                displayErrorMessage(t.message.toString())
            }

            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (!response.isSuccessful) {
                    displayErrorMessage(response.message().toString())
                    return
                }
                response.body()?.let {
                    val pageInformation = vm.getCurrentPageAndTotalPages(it)
                    currentPage = pageInformation.first
                    totalPages = pageInformation.second
                    val convertedJson = vm.convertJsonToMovieList(it)
                    convertedJson?.let { converted ->
                        val adapterList =
                            vm.createMovieListItems(currentPage, totalPages, converted)
                        populateList(adapterList)
                    }
                }
            }
        })
    }

    fun populateList(movieList: List<MovieListItems>) {
        allMovieList.apply {
            layoutManager = LinearLayoutManager(context)
            movieAdapter = MovieAdapter(onNextOrPreviousClicked, onItemClicked)
            adapter = movieAdapter
        }
        movieAdapter.submitMovieDetails(movieList)
    }

    fun displayErrorMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val BASE_IMAGE_URL = "http://image.tmdb.org/t/p/original"
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val API_KEY = "f37fcd6242a1b823af26107652db1803"
    }
}

