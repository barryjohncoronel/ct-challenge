package com.example.cartrack.dagger

import com.example.cartrack.ui.adduser.AddUserActivity
import com.example.cartrack.ui.adduser.AddUserModule
import com.example.cartrack.ui.login.LoginActivity
import com.example.cartrack.ui.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity
}