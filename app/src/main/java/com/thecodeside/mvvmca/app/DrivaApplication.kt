package com.thecodeside.mvvmca.app

import com.thecodeside.mvvmca.injection.BaseSystemModule
import dagger.android.support.DaggerApplication

class DrivaApplication : DaggerApplication() {

    override fun applicationInjector() = applicationComponent

    override fun onCreate() {
        initializeDI()
        super.onCreate()

        applicationComponent.inject(this)
    }

    private fun initializeDI() {
        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .baseSystemModule(BaseSystemModule(this))
            .build()
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }
}
