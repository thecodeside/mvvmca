package com.thecodeside.mvvmca.features.drivers

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.thecodeside.mvvmca.common.base.BaseRxViewModel
import com.thecodeside.mvvmca.common.util.applyDefaultThreads
import com.thecodeside.mvvmca.features.drivers.usecase.DriversRemoteToRecyclerItemMapper
import com.thecodeside.mvvmca.features.drivers.usecase.GetDriversList
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val LOG_TAG = "DriversListViewModel"
private const val INITIAL_DELAY = 0L
private const val REFRESH_INTERVAL_SECONDS = 5L

class DriversListViewModel @Inject constructor(
    private val getDriversList: GetDriversList,
    private val driversRemoteToRecyclerItemMapper: DriversRemoteToRecyclerItemMapper
) : BaseRxViewModel() {

    val subscriptionState = MutableLiveData<SubscriptionState>()
        .apply { value = SubscriptionState.PLAY }

    val driversList = MutableLiveData<List<DriversItem>>()

    @SuppressLint("MissingPermission")
    fun toggleDriversSubscription() {
        when (subscriptionState.value) {
            SubscriptionState.PLAY -> startSubscription()
            SubscriptionState.PAUSE -> stopSubscription()
        }
    }

    private fun stopSubscription() {
        subscriptionState.value = SubscriptionState.PLAY
        compositeDisposable.clear()
    }

    private fun startSubscription() {
        Observable.interval(INITIAL_DELAY, REFRESH_INTERVAL_SECONDS, TimeUnit.SECONDS)
            .doOnSubscribe { subscriptionState.postValue(SubscriptionState.PAUSE) }
            .flatMap { getDriversList() }
            .map { driversRemoteToRecyclerItemMapper(it) }
            .applyDefaultThreads()
            .subscribe({
                driversList.value = it
            }, {
                Log.e(LOG_TAG, "Error on receiving drivers list", it)
            }).registerSubscription()
    }
}