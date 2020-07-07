package com.mobile.movies.domain.interactor

import com.mobile.movies.domain.common.BaseFlowableInteractor
import com.mobile.movies.domain.entities.MoviesData
import com.mobile.movies.domain.repositories.MoviesRepository
import retrofit2.Response

class GetMoviesInteractor(
    private val repositories: MoviesRepository
) : BaseFlowableInteractor() {

    suspend fun getMovies(
        page: Int
    ): Response<MoviesData> {
        return repositories.getMovies(page)
    }
}