package com.thecodeside.mvvmca.common.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.applyDefaultThreads() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.applyDefaultThreads() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun Completable.applyDefaultThreads() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Observable<T>.applyDefaultThreads() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.applyDefaultThreads() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())