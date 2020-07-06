package com.mobile.movies.data.services

import com.mobile.movies.data.entities.MoviesData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServices {

    @GET("/3/movie/top_rated")
    fun movies(@Query("page") page: Int): Flowable<MoviesData>

}
 