package com.example.ct.data.service.login

import com.example.ct.data.service.localusercredentials.LocalUserCredentialsModule
import dagger.Binds
import dagger.Module

@Module(includes = [LocalUserCredentialsModule::class])
interface LoginServiceModule {

    @Binds
    fun bindLoginService(
        loginServiceImpl: LoginServiceImpl
    ): LoginService
}