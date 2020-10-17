package com.example.moviedatabase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedatabase.R

class MovieAdapter(
    private val listener: NextPageViewHolder.OnNextOrPreviousClicked,
    private val onItemClicked: MovieListViewHolder.OnItemClicked
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<MovieListItems> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            MOVIE_LIST_ITEM -> MovieListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
            )
            NEXT_PAGE -> NextPageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.next_page_item, parent, false)
            )
            else -> MovieListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
            )
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieListViewHolder -> {
                holder.bind(items[position], onItemClicked)
            }
            is NextPageViewHolder -> {
                holder.bind(items[position], listener)
            }
        }
    }

    fun submitMovieDetails(movieList: List<MovieListItems>) {
        items = movieList
    }

    override fun getItemViewType(position: Int) = when (items[position]) {
        is MovieItemForList -> MOVIE_LIST_ITEM
        is NextPageItemForList -> NEXT_PAGE
        else -> -1
    }

    companion object {
        private const val MOVIE_LIST_ITEM = 1
        private const val NEXT_PAGE = 2
    }

}