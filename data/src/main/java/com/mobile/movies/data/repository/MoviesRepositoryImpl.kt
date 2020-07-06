package com.mobile.movies.data.repository

import com.mobile.movies.data.services.MoviesServices
import com.mobile.movies.data.entities.MoviesDataEntityMapper
import com.mobile.movies.domain.entities.MoviesEntity
import com.mobile.movies.domain.repositories.MoviesRepository
import io.reactivex.Flowable

class MoviesRepositoryImpl constructor(private val moviesServices: MoviesServices) :
    MoviesRepository {

    private val moviesMapper = MoviesDataEntityMapper()

    override fun getMovies(
        page: Int
    ): Flowable<MoviesEntity> {
        return moviesServices.movies(page).map { moviesMapper.mapToEntity(it) }
    }

}
