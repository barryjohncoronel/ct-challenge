package com.example.ct.data.service.addusercredentials

import com.example.ct.data.service.localusercredentials.LocalUserCredentialsModule
import dagger.Binds
import dagger.Module

@Module(includes = [LocalUserCredentialsModule::class])
interface AddUserCredentialsServiceModule {

    @Binds
    fun bindAddUserCredentialsService(
        addUserServiceImpl: AddUserCredentialsServiceImpl
    ): AddUserCredentialsService
}