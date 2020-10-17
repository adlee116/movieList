package com.example.moviedatabase.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedatabase.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.movie_details_bottom_sheet.*

class MovieDetailsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_details_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireContext().getSharedPreferences("MovieApp", 0)
        val id = arguments?.getString("id")
        val title = arguments?.getString("title")
        val poster = arguments?.getString("poster")
        val overview = arguments?.getString("overview")
        val favs = getPreviousFavourites()
        if(favs.contains(id)) {
            favouriteStar.isSelected = true
        }
        favouriteStar.setOnClickListener {
            id?.let {
                setFav(!favouriteStar.isSelected, it)
                favouriteStar.isSelected = !favouriteStar.isSelected
            }
        }
        titleText.text = title
        movieInformation.text = overview
        context?.let {


            //TODO Have a base class to inherit from for activities / fragments that takes care of things such as glide
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(it)
                .applyDefaultRequestOptions(requestOptions)
                .load(poster)
                .into(image)
        }
    }

    //TODO all shared pref stuff needs to be extracted out to a repo, which would then allow us to
    //TODO to ensure that we could ensure this project was clean and coupling was minimised

    private fun getPreviousFavourites() : MutableList<String> {
        return if(prefs.contains("favourites")) {
            prefs.getStringSet("favourites", null)?.let {
                it.toMutableList()
            } ?: run {
                mutableListOf<String>()
            }
        } else {
            mutableListOf()
        }
    }

    private fun setFav(setToFav: Boolean, id: String) {
        val favourites = getPreviousFavourites()
        if(setToFav){
            favourites.add(id)
        } else {
            favourites.remove(id)
        }
        val editor = prefs.edit()
        editor.putStringSet("favourites", favourites.toSet())
        editor.apply()
    }

    companion object {

        fun open(fragmentManager: FragmentManager?, movieDetails: MovieItemForList): MovieDetailsBottomSheet? {
            if (fragmentManager == null) {
                return null
            }
            val bundle = Bundle()
            bundle.putString("id", movieDetails.id)
            bundle.putString("title", movieDetails.originalTitle)
            bundle.putString("poster", movieDetails.posterPath)
            bundle.putString("overview", movieDetails.overview)
            val fragment = MovieDetailsBottomSheet()
            fragment.arguments = bundle
            fragment.show(fragmentManager, "MovieDetailsBottomSheet")
            return fragment
        }
    }
}