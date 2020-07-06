package com.mobile.movies.domain.entities

data class MoviesItemEntity(
    var popularity: Double,
    var vote_average: Double,
    var title: String,
    var overview: String,
    var release_date: String,
    var poster_path: String
)