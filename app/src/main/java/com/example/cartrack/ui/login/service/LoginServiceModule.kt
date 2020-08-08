package com.example.cartrack.ui.login.service

import dagger.Binds
import dagger.Module

@Module
interface LoginServiceModule {

    @Binds
    fun bindLoginService(
        loginServiceImpl: LoginServiceImpl
    ): LoginService
}