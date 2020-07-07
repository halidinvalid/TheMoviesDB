package com.mobile.movies.presentation.extension

import androidx.lifecycle.MutableLiveData
import com.mobile.movies.presentation.entities.DataHolder
import com.mobile.movies.presentation.entities.Error
import com.mobile.movies.presentation.entities.Status
import retrofit2.Response


fun <T : Any?> MutableLiveData<DataHolder<T?>>.loadingData() = apply {
    postValue(DataHolder(responseType = Status.LOADING))
}


fun <T : Any?> MutableLiveData<DataHolder<T?>>.responseData(responseMethod: Response<T>) = apply {
    responseMethod.apply {
        if (isSuccessful) {
            postValue(
                DataHolder(responseType = Status.SUCCESSFUL, data = this.body())
            )
        } else {
            postValue(
                DataHolder(
                    responseType = Status.ERROR,
                    error = Error("invalid response")
                )
            )
        }
    }
}