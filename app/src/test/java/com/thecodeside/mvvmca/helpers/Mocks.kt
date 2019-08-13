package com.thecodeside.mvvmca.helpers

import android.location.Location
import com.thecodeside.mvvmca.network.DriverRemoteModel

val mockCoordinate = DriverRemoteModel.Coordinates(1.0, 1.0)

val mockDriverRemoteModel = DriverRemoteModel(1, "firstname", "lastname", "image", mockCoordinate)

val mockDriverRemoteModelList = listOf(mockDriverRemoteModel)

val mockLocation = Location("test")