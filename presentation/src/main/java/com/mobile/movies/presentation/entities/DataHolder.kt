package com.mobile.movies.presentation.entities

data class DataHolder<T>(
    var responseType: Status,
    var data: T? = null,
    var error: Error? = null
)

sealed class Status {
    object SUCCESSFUL : Status()
    object ERROR : Status()
    object LOADING : Status()
}