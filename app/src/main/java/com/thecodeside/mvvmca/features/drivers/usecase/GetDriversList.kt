package com.thecodeside.mvvmca.features.drivers.usecase

import android.annotation.SuppressLint
import com.thecodeside.mvvmca.common.base.BaseUseCase
import com.thecodeside.mvvmca.network.ApiInterface
import com.thecodeside.mvvmca.network.CoordinatesBody
import com.thecodeside.mvvmca.network.DriverRemoteModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider
import javax.inject.Inject

class GetDriversList @Inject constructor(
    private val reactiveLocationProvider: ReactiveLocationProvider,
    private val api: ApiInterface
) : BaseUseCase {

    @SuppressLint("MissingPermission")
    operator fun invoke(): Observable<List<DriverRemoteModel>> = reactiveLocationProvider.lastKnownLocation
        .observeOn(Schedulers.io())
        .map(::CoordinatesBody)
        .flatMapSingle(api::getCoordinates)
}
