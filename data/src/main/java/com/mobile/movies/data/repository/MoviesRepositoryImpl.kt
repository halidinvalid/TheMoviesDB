package com.mobile.movies.data.repository

import com.mobile.movies.data.services.MoviesServices
import com.mobile.movies.domain.entities.MoviesData
import com.mobile.movies.domain.repositories.MoviesRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MoviesRepositoryImpl constructor(private val moviesServices: MoviesServices) :
    MoviesRepository {

    override suspend fun getMovies(page: Int): Response<MoviesData> {
        return moviesServices.movies(page)
    }
}
