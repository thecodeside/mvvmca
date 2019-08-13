package com.thecodeside.mvvmca.features.drivers

import com.thecodeside.mvvmca.common.base.BaseActivity
import dagger.Binds
import dagger.Module


@Module
abstract class DriversListBindingModule {

    @Binds
    abstract fun bindBaseActivity(driversListActivity: DriversListActivity): BaseActivity
}
