package com.example.cartrack.dagger

import com.example.cartrack.ui.addusercredentials.AddUserCredentialsActivity
import com.example.cartrack.ui.addusercredentials.AddUserCredentialsModule
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

    @ContributesAndroidInjector(modules = [AddUserCredentialsModule::class])
    abstract fun contributeAddUserActivity(): AddUserCredentialsActivity

    @ContributesAndroidInjector(modules = [UsersModule::class])
    abstract fun contributeMainActivity(): UsersActivity

    @ContributesAndroidInjector(modules = [SelectCountryModule::class])
    abstract fun contributeSelectCountryActivity(): SelectCountryActivity
}