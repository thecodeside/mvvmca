package com.thecodeside.mvvmca.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.PUT

interface ApiInterface {

    @PUT("coordinates")
    fun getCoordinates(@Body coordinatesBody: CoordinatesBody): Single<List<DriverRemoteModel>>

}