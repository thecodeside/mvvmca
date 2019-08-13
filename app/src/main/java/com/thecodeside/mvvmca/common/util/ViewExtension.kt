package com.thecodeside.mvvmca.common.util

import android.view.View
import android.widget.ImageButton
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

private const val DEBOUNCE_TIMEOUT = 300L

fun ImageButton.debounceClicks() = this.clicks()
    .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())