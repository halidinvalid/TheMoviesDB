package com.mobile.movies.domain.model

import com.google.gson.annotations.SerializedName

data class MoviesData(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<MoviesItemData>
)









