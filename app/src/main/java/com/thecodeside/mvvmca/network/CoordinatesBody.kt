package com.thecodeside.mvvmca.network

import android.location.Location

data class CoordinatesBody(
    val latitude: Double,
    val longitude: Double
) {
    constructor(location: Location) : this(location.latitude, location.longitude)
}