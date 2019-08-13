package com.thecodeside.mvvmca.location

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider


@Module
class LocationModule {

    @Provides
    fun provideReactiveLocationProvider(context: Context): ReactiveLocationProvider = ReactiveLocationProvider(context)
    
}