package com.mobile.movies.presentation.entities


data class MoviesItem(
    var popularity: Double,
    var vote_average: Double,
    var title: String,
    var overview: String,
    var release_date: String,
    var poster_path: String
)