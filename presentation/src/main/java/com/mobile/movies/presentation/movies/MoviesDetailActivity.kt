package com.mobile.movies.presentation.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.movies.presentation.R
import com.mobile.movies.presentation.di.IMAGE_URL
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movies.*

class MoviesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        intent.apply {
            supportActionBar?.apply {
                title = getStringExtra(BUNDLE_TITLE)
                setDisplayHomeAsUpEnabled(true)
            }
            Picasso.with(this@MoviesDetailActivity)
                .load(IMAGE_URL + getStringExtra(BUNDLE_PATH))
                .networkPolicy(NetworkPolicy.NO_CACHE).placeholder(R.drawable.placeholder)
                .into(imageViewPoster)

            textViewOverView.text = getStringExtra(BUNDLE_OVERVIEW)
            textViewPopularity.text = getStringExtra(BUNDLE_POPULARITY)
            textViewAverage.text = getStringExtra(BUNDLE_AVERAGE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val BUNDLE_TITLE = "title"
        const val BUNDLE_PATH = "path"
        const val BUNDLE_OVERVIEW = "overview"
        const val BUNDLE_POPULARITY = "popularity"
        const val BUNDLE_AVERAGE = "average"
    }
}