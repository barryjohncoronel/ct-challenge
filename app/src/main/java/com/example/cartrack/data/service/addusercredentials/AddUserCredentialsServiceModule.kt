package com.example.cartrack.data.service.addusercredentials

import dagger.Binds
import dagger.Module

@Module
interface AddUserCredentialsServiceModule {

    @Binds
    fun bindAddUserCredentialsService(
        addUserServiceImpl: AddUserCredentialsServiceImpl
    ): AddUserCredentialsService
}