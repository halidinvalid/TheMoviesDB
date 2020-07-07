package com.mobile.movies.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.movies.domain.interactor.GetMoviesInteractor
import com.mobile.movies.domain.model.MoviesData
import com.mobile.movies.presentation.common.BaseViewModel
import com.mobile.movies.presentation.entities.DataHolder
import com.mobile.movies.presentation.extension.launchViewModelScope
import com.mobile.movies.presentation.extension.loadingData
import com.mobile.movies.presentation.extension.responseData

class MoviesViewModel(
    private val getMoviesInteractor: GetMoviesInteractor
) : BaseViewModel() {

    private val _moviesLiveData = MutableLiveData<DataHolder<MoviesData?>>()
    val moviesLiveData: LiveData<DataHolder<MoviesData?>>
        get() = _moviesLiveData

    fun getMovies(
        page: Int
    ) = launchViewModelScope {
        _moviesLiveData
            .loadingData()
            .responseData(
                getMoviesInteractor.getMovies(
                    page
                )
            )
    }

    companion object {
        private const val TAG = "MoviesViewModel"
    }
}
