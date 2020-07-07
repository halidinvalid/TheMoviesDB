package com.mobile.movies.domain.repositories

import com.mobile.movies.domain.entities.MoviesData
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

interface MoviesRepository {

   suspend fun getMovies(
        page: Int
    ): Response<MoviesData>
}