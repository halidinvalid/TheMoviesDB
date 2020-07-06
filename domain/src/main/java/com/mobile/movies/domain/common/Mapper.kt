package com.mobile.movies.domain.common

import io.reactivex.Flowable

abstract class Mapper<in T, E> {

    abstract fun mapFrom(from: T): E

    fun flowable(from: T): Flowable<E> = Flowable.fromCallable { mapFrom(from) }

}