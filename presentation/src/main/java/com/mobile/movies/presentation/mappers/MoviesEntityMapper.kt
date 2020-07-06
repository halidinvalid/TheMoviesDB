package com.mobile.movies.presentation.mappers


import com.mobile.movies.domain.common.Mapper
import com.mobile.movies.domain.entities.MoviesEntity
import com.mobile.movies.presentation.entities.Movies
import com.mobile.movies.presentation.entities.MoviesItem

class MoviesEntityMapper : Mapper<MoviesEntity, Movies>() {

    override fun mapFrom(from: MoviesEntity): Movies = Movies(
        page = from.page,
        results = from.results.map {
            MoviesItem(
                it.popularity,
                it.vote_average,
                it.title,
                it.overview,
                it.release_date,
                it.poster_path
            )
        } as MutableList<MoviesItem>
    )
}