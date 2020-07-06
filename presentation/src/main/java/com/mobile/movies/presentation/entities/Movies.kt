package com.mobile.movies.presentation.entities

data class Movies(
    var page: Int = 0,
    var results: MutableList<MoviesItem>
)