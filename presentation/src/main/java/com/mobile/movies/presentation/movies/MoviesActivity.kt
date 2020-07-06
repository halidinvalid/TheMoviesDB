package com.mobile.movies.presentation.movies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.movies.presentation.R
import com.mobile.movies.presentation.entities.MoviesItem
import com.mobile.movies.presentation.entities.Status
import com.mobile.movies.presentation.extension.observeApi
import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var listAdapter: MoviesListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private var loadingPage = 1

    private var moviesList = mutableListOf<MoviesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        layoutManager = GridLayoutManager(this, 2)
        recyclerViewMovies.layoutManager = layoutManager
        moviesViewModel.getMovies(loadingPage)

        buttonLoadMore.setOnClickListener {
            loadingPage++
            moviesViewModel.getMovies(loadingPage)
        }

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

    override fun onStart() {
        super.onStart()
        moviesList.clear()
        moviesViewModel.moviesLiveData.observeApi(this, { response ->
            when (response.responseType) {
                Status.ERROR -> {
                    Toast.makeText(this, response.error.toString(), Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
                }
                Status.SUCCESSFUL -> {
                    response.data.let { data ->
                        if (data != null) {
                            moviesList.addAll(data.results)
                            listAdapter = MoviesListAdapter(moviesList)
                            recyclerViewMovies.adapter = listAdapter
                        }
                    }
                }
            }

        })
    }
}