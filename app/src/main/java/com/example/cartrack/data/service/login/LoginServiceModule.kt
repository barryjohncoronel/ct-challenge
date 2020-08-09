package com.example.cartrack.data.service.login

import dagger.Binds
import dagger.Module

@Module
interface LoginServiceModule {

    @Binds
    fun bindLoginService(
        loginServiceImpl: LoginServiceImpl
    ): LoginService
}