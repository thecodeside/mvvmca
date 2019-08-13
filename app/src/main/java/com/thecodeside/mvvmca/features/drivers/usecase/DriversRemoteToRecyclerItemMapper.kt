package com.thecodeside.mvvmca.features.drivers.usecase

import com.thecodeside.mvvmca.common.base.BaseUseCase
import com.thecodeside.mvvmca.features.drivers.DriversItem
import com.thecodeside.mvvmca.network.DriverRemoteModel
import javax.inject.Inject

class DriversRemoteToRecyclerItemMapper @Inject constructor() : BaseUseCase {

    operator fun invoke(list: List<DriverRemoteModel>) = list.map(::DriversItem)
}
