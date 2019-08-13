package com.thecodeside.mvvmca.location

import android.Manifest
import com.thecodeside.mvvmca.common.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import javax.inject.Inject


private val permissions =
    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

class LocationPermissionManager @Inject constructor(private val activity: BaseActivity) {

    fun isLocationPermissionGranted() = permissions.all { RxPermissions(activity).isGranted(it) }

    fun requestLocationPermission(): Observable<Boolean> = RxPermissions(activity).request(*permissions)
}