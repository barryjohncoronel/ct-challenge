package com.example.cartrack.dagger

import com.example.cartrack.ui.adduser.AddUserActivity
import com.example.cartrack.ui.adduser.AddUserModule
import com.example.cartrack.ui.login.LoginActivity
import com.example.cartrack.ui.login.LoginModule
import com.example.cartrack.ui.main.MainActivity
import com.example.cartrack.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [AddUserModule::class])
    abstract fun contributeAddUserActivity(): AddUserActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}