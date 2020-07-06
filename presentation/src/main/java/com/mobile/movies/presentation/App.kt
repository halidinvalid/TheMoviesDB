package com.mobile.movies.presentation

import android.app.Application
import com.mobile.movies.presentation.di.*
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(
            this, listOf(
                networkModules,
                viewModelModules,
                repositoryModules,
                interactorModules
            )
        )
    }
}