package com.example.cartrack.data.service.login

import com.example.cartrack.data.service.local.LocalUserCredentialsModule
import dagger.Binds
import dagger.Module

@Module(includes = [LocalUserCredentialsModule::class])
interface LoginServiceModule {

    @Binds
    fun bindLoginService(
        loginServiceImpl: LoginServiceImpl
    ): LoginService
}