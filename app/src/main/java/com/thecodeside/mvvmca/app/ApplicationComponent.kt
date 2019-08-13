package com.thecodeside.mvvmca.app

import android.content.Context
import com.thecodeside.mvvmca.injection.AndroidScreenInjectionModule
import com.thecodeside.mvvmca.injection.BaseSystemModule
import com.thecodeside.mvvmca.injection.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidScreenInjectionModule::class,
        BaseSystemModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DrivaApplication> {

    override fun inject(application: DrivaApplication)

    fun getContext(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DrivaApplication): Builder

        fun baseSystemModule(systemModule: BaseSystemModule): Builder

        fun build(): ApplicationComponent
    }
}
