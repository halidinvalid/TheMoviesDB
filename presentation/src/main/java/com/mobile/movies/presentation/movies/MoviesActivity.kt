package com.mobile.movies.presentation.movies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.mobile.movies.domain.model.MoviesItemData
import com.mobile.movies.presentation.R
import com.mobile.movies.presentation.extension.observeResponse
import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var listAdapter: MoviesListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private var loadingPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        listAdapter = MoviesListAdapter()
        layoutManager = GridLayoutManager(this, 2)
        recyclerViewMovies.layoutManager = layoutManager
        recyclerViewMovies.adapter = listAdapter
        moviesViewModel.getMovies(loadingPage)

        buttonLoadMore.setOnClickListener {
            loadingPage++
            moviesViewModel.getMovies(loadingPage)
        }

        moviesViewModel.moviesLiveData.observeResponse(
            owner = this,
            loading = { loading ->
                Toast.makeText(this, loading, Toast.LENGTH_LONG).show()
            },
            fail = { error ->
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            },
            success = { moviesData ->
                moviesData?.results?.let {
                    listAdapter.updateList(moviesData.results as MutableList<MoviesItemData>)
                }
            }
        )

        searchViewMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty() || newText.length > 2)
                    listAdapter.filter.filter(newText)
                return false
            }
        })
    }

}