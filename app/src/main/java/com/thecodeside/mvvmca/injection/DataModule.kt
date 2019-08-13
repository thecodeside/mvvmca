package com.thecodeside.mvvmca.injection


import com.thecodeside.mvvmca.network.ApiInterface
import com.thecodeside.mvvmca.network.NetworkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(): ApiInterface = NetworkManager().getRepository()
}
