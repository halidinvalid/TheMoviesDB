package com.mobile.movies.data.entities

import com.google.gson.annotations.SerializedName
import com.mobile.movies.domain.entities.MoviesEntity
import com.mobile.movies.domain.entities.MoviesItemEntity

data class MoviesData(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<MoviesItemData> = emptyList()
)

class MoviesDataEntityMapper {

    fun mapToEntity(data: MoviesData?): MoviesEntity? = MoviesEntity(
        page = data!!.page,
        results = mapListMoviesToEntity(data.results)
    )

    fun mapListMoviesToEntity(movies: List<MoviesItemData>?)
            : List<MoviesItemEntity> = movies?.map { mapMoviesToEntity(it) } ?: emptyList()

    fun mapMoviesToEntity(response: MoviesItemData): MoviesItemEntity = MoviesItemEntity(
        popularity = response.popularity,
        vote_average = response.vote_average,
        title = response.title.toString(),
        overview = response.overview.toString(),
        release_date = response.release_date.toString(),
        poster_path = response.poster_path.toString()
    )
}









