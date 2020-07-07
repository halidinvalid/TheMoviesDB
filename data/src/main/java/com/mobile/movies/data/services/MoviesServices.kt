package com.mobile.movies.data.services

import com.mobile.movies.domain.model.MoviesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServices {

    @GET("/3/movie/top_rated")
    suspend fun movies(@Query("page") page: Int): Response<MoviesData>

}
 