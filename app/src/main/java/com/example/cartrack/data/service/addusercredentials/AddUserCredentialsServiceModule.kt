package com.example.cartrack.data.service.addusercredentials

import com.example.cartrack.data.service.local.LocalUserCredentialsModule
import dagger.Binds
import dagger.Module

@Module(includes = [LocalUserCredentialsModule::class])
interface AddUserCredentialsServiceModule {

    @Binds
    fun bindAddUserCredentialsService(
        addUserServiceImpl: AddUserCredentialsServiceImpl
    ): AddUserCredentialsService
}