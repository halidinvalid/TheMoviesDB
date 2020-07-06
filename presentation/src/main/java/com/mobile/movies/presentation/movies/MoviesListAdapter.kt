package com.mobile.movies.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mobile.movies.presentation.R
import com.mobile.movies.presentation.di.IMAGE_URL
import com.mobile.movies.presentation.entities.MoviesItem
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item.view.*
import java.util.*


class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>(), Filterable {

    var movies = mutableListOf<MoviesItem>()
    var moviesFilter = mutableListOf<MoviesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return MoviesViewHolder(view)

    }

    override fun getItemCount(): Int {
        return moviesFilter.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesFilter[position])
    }

    fun updateList(list: List<MoviesItem>) {
        movies.addAll(list)
        moviesFilter.addAll(list)
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(moviesItem: MoviesItem) {
            with(itemView) {
                textViewTitle.text = moviesItem.title
                Picasso.with(context)
                    .load(IMAGE_URL + moviesItem.poster_path)
                    .networkPolicy(NetworkPolicy.NO_CACHE).placeholder(R.drawable.placeholder)
                    .into(imageViewPoster)

                setOnClickListener {
                    val intent = Intent(context, MoviesDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.apply {
                        putString(MoviesDetailActivity.BUNDLE_TITLE, moviesItem.title)
                        putString(
                            MoviesDetailActivity.BUNDLE_AVERAGE,
                            moviesItem.vote_average.toString()
                        )
                        putString(MoviesDetailActivity.BUNDLE_OVERVIEW, moviesItem.overview)
                        putString(MoviesDetailActivity.BUNDLE_PATH, moviesItem.poster_path)
                        putString(
                            MoviesDetailActivity.BUNDLE_POPULARITY,
                            moviesItem.popularity.toString()
                        )
                    }
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                moviesFilter = if (charSearch.isEmpty()) {
                    movies
                } else {
                    val resultList = mutableListOf<MoviesItem>()
                    for (row in movies) {
                        if (row.title.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.count = moviesFilter.size
                filterResults.values = moviesFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                moviesFilter = results?.values as MutableList<MoviesItem>
                notifyDataSetChanged()
            }

        }
    }
}