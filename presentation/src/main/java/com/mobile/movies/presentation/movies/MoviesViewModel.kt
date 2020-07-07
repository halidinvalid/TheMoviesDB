package com.mobile.movies.presentation.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobile.movies.domain.interactor.GetMoviesInteractor
import com.mobile.movies.domain.model.MoviesData
import com.mobile.movies.presentation.common.BaseViewModel
import com.mobile.movies.presentation.entities.DataHolder
import com.mobile.movies.presentation.entities.Error
import com.mobile.movies.presentation.entities.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
    private val getMoviesInteractor: GetMoviesInteractor
) : BaseViewModel() {

    private val _moviesLiveData = MutableLiveData<DataHolder<MoviesData>>()
    val moviesLiveData: LiveData<DataHolder<MoviesData>>
        get() = _moviesLiveData

    fun getMovies(
        page: Int
    ) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.Default) {
                    getMoviesInteractor.getMovies(
                        page
                    )
                }.body()

                _moviesLiveData.value =
                    DataHolder(responseType = Status.SUCCESSFUL, data = response)

            } catch (exception: Exception) {
                _moviesLiveData.value =
                    DataHolder(responseType = Status.ERROR, error = Error(exception.message))
                Log.e("TAG", exception.message)
            }
        }
    }

    companion object {
        private const val TAG = "MoviesViewModel"
    }
}
