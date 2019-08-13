package com.thecodeside.mvvmca.common.base

import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseActivity : DaggerAppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    protected fun Disposable.registerSubscription() {
        compositeDisposable.add(this)
    }
}
