package com.mobile.movies.presentation.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.movies.domain.common.Mapper
import com.mobile.movies.domain.entities.MoviesEntity
import com.mobile.movies.domain.interactor.GetMoviesInteractor
import com.mobile.movies.presentation.common.BaseViewModel
import com.mobile.movies.presentation.entities.DataHolder
import com.mobile.movies.presentation.entities.Error
import com.mobile.movies.presentation.entities.Movies
import com.mobile.movies.presentation.entities.Status

class MoviesViewModel(
    private val getMoviesInteractor: GetMoviesInteractor,
    private val mapper: Mapper<MoviesEntity, Movies>
) : BaseViewModel() {

    private val _moviesLiveData = MutableLiveData<DataHolder<Movies>>()
    val moviesLiveData: LiveData<DataHolder<Movies>>
        get() = _moviesLiveData

    fun getMovies(
        page: Int
    ) {
        val disposable = getMoviesInteractor.getMovies(page)
            .flatMap { mapper.flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On next Called")
                _moviesLiveData.value = DataHolder(
                    responseType = Status.SUCCESSFUL,
                    data = response
                )
            }, { error ->
                Log.d(TAG, "On error Called")
                _moviesLiveData.value = DataHolder(
                    responseType = Status.ERROR,
                    error = Error(error.message)
                )
            }, {
                Log.d(TAG, "On complete Called")
            })
        addDisposable(disposable)
    }

    companion object {
        private const val TAG = "MoviesViewModel"
    }
}
