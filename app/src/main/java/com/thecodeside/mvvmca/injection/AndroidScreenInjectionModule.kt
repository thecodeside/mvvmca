package com.thecodeside.mvvmca.injection

import com.thecodeside.mvvmca.features.drivers.DriversListActivity
import com.thecodeside.mvvmca.features.drivers.DriversListBindingModule
import com.thecodeside.mvvmca.location.LocationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidScreenInjectionModule {

    @ContributesAndroidInjector(modules = [DriversListBindingModule::class, LocationModule::class])
    @PerActivity
    abstract fun contributeDriversListActivity(): DriversListActivity
}
