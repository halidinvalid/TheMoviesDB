package com.mobile.movies.presentation.entities

data class DataHolder<T>(
    var responseType: Status,
    var data: T? = null,
    var error: Error? = null
)

enum class Status {
    SUCCESSFUL,
    ERROR,
    LOADING
}