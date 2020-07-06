package com.mobile.movies.domain.repositories

import com.mobile.movies.domain.entities.MoviesEntity
import io.reactivex.Flowable

interface MoviesRepository {

    fun getMovies(
        page: Int
    ): Flowable<MoviesEntity>
}