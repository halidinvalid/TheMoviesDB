package com.mobile.movies.domain.repositories

import com.mobile.movies.domain.model.MoviesData
import retrofit2.Response

interface MoviesRepository {

   suspend fun getMovies(
        page: Int
    ): Response<MoviesData>
}