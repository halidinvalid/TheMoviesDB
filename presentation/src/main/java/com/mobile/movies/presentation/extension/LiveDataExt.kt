package com.mobile.movies.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mobile.movies.presentation.entities.DataHolder
import com.mobile.movies.presentation.entities.Error
import com.mobile.movies.presentation.entities.Status

inline fun <T : Any?> LiveData<DataHolder<T?>>.observeResponse(
    owner: LifecycleOwner,
    crossinline success: ((T?) -> Unit) = {
        // no-op
    },
    crossinline fail: ((Error) -> Unit) = {
        // no-op
    },
    crossinline loading: ((T?) -> Unit) = {
        //no-op
    }
) {
    observe(owner, Observer { holder: DataHolder<T?>? ->
        when (holder?.responseType) {
            is Status.SUCCESSFUL -> success(holder.data)
            is Status.ERROR -> error(holder.error?.message.toString())
        }
    })
}

