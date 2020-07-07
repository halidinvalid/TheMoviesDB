package com.mobile.movies.presentation.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun ViewModel.launchViewModelScope(scopeMethod: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launchCoroutineScope(scopeMethod)
}

fun CoroutineScope.launchCoroutineScope(coroutineMethod: suspend CoroutineScope.() -> Unit) {
    launch {
        withContext(Dispatchers.Default) {
            coroutineMethod()
        }
    }
}
