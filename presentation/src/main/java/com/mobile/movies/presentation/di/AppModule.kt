package com.mobile.movies.presentation.di

import com.mobile.movies.data.services.MoviesServices
import com.mobile.movies.data.repository.MoviesRepositoryImpl
import com.mobile.movies.domain.repositories.MoviesRepository
import com.mobile.movies.domain.interactor.GetMoviesInteractor
import com.mobile.movies.presentation.common.AsyncFlowableTransformer
import com.mobile.movies.presentation.mappers.MoviesEntityMapper
import com.mobile.movies.presentation.movies.MoviesViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val repositoryModules = module {
    single {
        provideMoviesRepository(moviesServices = get())

    }

}

val interactorModules = module {
    factory(name = GET_MOVIES_INTERACTOR) {
        GetMoviesInteractor(transformer = AsyncFlowableTransformer(), repositories = get())
    }
}

val networkModules = module {
    single(name = RETROFIT_INSTANCE) {
        createNetworkClient(BASE_URL)
    }
    single(name = API) {
        (get(RETROFIT_INSTANCE) as Retrofit).create(MoviesServices::class.java)
    }
}

val viewModelModules = module {
    viewModel {
        MoviesViewModel(
            getMoviesInteractor = get(GET_MOVIES_INTERACTOR),
            mapper = MoviesEntityMapper()
        )
    }
}

private fun provideMoviesRepository(moviesServices: MoviesServices): MoviesRepository =
    MoviesRepositoryImpl(moviesServices = moviesServices)

const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
private const val BASE_URL = "https://api.themoviedb.org/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_MOVIES_INTERACTOR = "getMoviesInteractor"
