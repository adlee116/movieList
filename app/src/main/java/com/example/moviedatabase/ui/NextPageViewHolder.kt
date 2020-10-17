package com.example.moviedatabase.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.next_page_item.view.*

class NextPageViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    interface OnNextOrPreviousClicked {
        fun loadAnotherPage(nextWasClicked: Boolean)
    }

    fun bind(nextPageItem: MovieListItems, listener: OnNextOrPreviousClicked?) {
        nextPageItem as NextPageItemForList
        containerView.nextPageButton.visibility = getVisibility(nextPageItem.nextAvailable)
        containerView.previousPageButton.visibility = getVisibility(nextPageItem.previousAvailable)
        containerView.nextPageButton.setOnClickListener{
            listener?.loadAnotherPage(true)
        }
        containerView.previousPageButton.setOnClickListener{
            listener?.loadAnotherPage(false)
        }
    }

    private fun getVisibility(visible: Boolean): Int {
        return if(visible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }


}