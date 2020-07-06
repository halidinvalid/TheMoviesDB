package com.mobile.movies.domain.entities

data class MoviesEntity(
    var page: Int = 0,
    var results: List<MoviesItemEntity> = emptyList()
)