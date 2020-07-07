package com.mobile.movies.domain.entities

import com.google.gson.annotations.SerializedName

data class MoviesItemData(
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("vote_average") var vote_average: Double,
    @SerializedName("title") var title: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("release_date") var release_date: String? = null,
    @SerializedName("poster_path") var poster_path: String? = null
)
