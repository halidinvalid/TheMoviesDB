package com.mobile.movies.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.movies.domain.interactor.GetMoviesInteractor
import com.mobile.movies.domain.model.MoviesData
import com.mobile.movies.presentation.common.BaseViewModel
import com.mobile.movies.presentation.entities.DataHolder
import com.mobile.movies.presentation.entities.Error
import com.mobile.movies.presentation.entities.Status
import com.mobile.movies.presentation.extension.launchViewModelScope

class MoviesViewModel(
    private val getMoviesInteractor: GetMoviesInteractor
) : BaseViewModel() {

    private val _moviesLiveData = MutableLiveData<DataHolder<MoviesData?>>()
    val moviesLiveData: LiveData<DataHolder<MoviesData?>>
        get() = _moviesLiveData

    fun getMovies(
        page: Int
    ) {
        _moviesLiveData.postValue(DataHolder(responseType = Status.LOADING))
        launchViewModelScope {
            getMoviesInteractor.getMovies(
                page
            ).apply {
                if (isSuccessful)
                    _moviesLiveData.postValue(
                        DataHolder(responseType = Status.SUCCESSFUL, data = this.body())
                    )
                else
                    _moviesLiveData.postValue(
                        DataHolder(
                            responseType = Status.ERROR,
                            error = Error("invalid response")
                        )
                    )
            }
        }
    }

    companion object {
        private const val TAG = "MoviesViewModel"
    }
}
