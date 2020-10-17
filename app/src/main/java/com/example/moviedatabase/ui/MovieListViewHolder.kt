package com.example.moviedatabase.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedatabase.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    interface OnItemClicked {
        fun onItemClicked(movieDetails: MovieListItems)
    }

    fun bind(movieDetails: MovieListItems, listener: OnItemClicked?) {
        movieDetails as MovieItemForList
        containerView.movieTitleText.text = movieDetails.originalTitle

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(movieDetails.posterPath)
            .into(containerView.movieImage)
        containerView.voteAverage.text = movieDetails.voteAverage
        containerView.releaseDate.text = movieDetails.releaseDate

        containerView.setOnClickListener {
            //TODO Would usually pass back just an ID to get the item from the DB
            listener?.onItemClicked(movieDetails)
        }
    }


}