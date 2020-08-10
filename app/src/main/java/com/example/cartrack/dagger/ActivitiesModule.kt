package com.example.cartrack.dagger

import com.example.cartrack.ui.adduser.AddUserActivity
import com.example.cartrack.ui.adduser.AddUserModule
import com.example.cartrack.ui.login.LoginActivity
import com.example.cartrack.ui.login.LoginModule
import com.example.cartrack.ui.selectcountry.SelectCountryActivity
import com.example.cartrack.ui.selectcountry.SelectCountryModule
import com.example.cartrack.ui.users.UsersActivity
import com.example.cartrack.ui.users.UsersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [AddUserModule::class])
    abstract fun contributeAddUserActivity(): AddUserActivity

    @ContributesAndroidInjector(modules = [UsersModule::class])
    abstract fun contributeMainActivity(): UsersActivity

    @ContributesAndroidInjector(modules = [SelectCountryModule::class])
    abstract fun contributeSelectCountryActivity(): SelectCountryActivity
}