package com.mobile.movies.domain.interactor

import com.mobile.movies.domain.common.BaseFlowableInteractor
import com.mobile.movies.domain.common.FlowableRxTransformer
import com.mobile.movies.domain.entities.MoviesEntity
import com.mobile.movies.domain.repositories.MoviesRepository
import io.reactivex.Flowable

class GetMoviesInteractor(
    private val transformer: FlowableRxTransformer<MoviesEntity>,
    private val repositories: MoviesRepository
) : BaseFlowableInteractor() {

    fun getMovies(
        page:Int
    ): Flowable<MoviesEntity> {
        return repositories.getMovies(page).compose(transformer)
    }
}