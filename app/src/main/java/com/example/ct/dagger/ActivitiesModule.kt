package com.example.ct.dagger

import com.example.ct.ui.addusercredentials.AddUserCredentialsActivity
import com.example.ct.ui.addusercredentials.AddUserCredentialsModule
import com.example.ct.ui.login.LoginActivity
import com.example.ct.ui.login.LoginModule
import com.example.ct.ui.selectcountry.SelectCountryActivity
import com.example.ct.ui.selectcountry.SelectCountryModule
import com.example.ct.ui.users.UsersActivity
import com.example.ct.ui.users.UsersModule
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