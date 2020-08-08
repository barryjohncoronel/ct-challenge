package com.example.cartrack.dagger

import android.app.Application
import com.example.cartrack.BaseApplication
import com.example.cartrack.data.db.AppDatabaseModule
import com.example.cartrack.data.network.ApiFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivitiesModule::class,
        ApiFactoryModule::class,
        AppDatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}