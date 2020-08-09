package com.example.cartrack.ui.adduser.service

import dagger.Binds
import dagger.Module

@Module
interface AddUserServiceModule {

    @Binds
    fun bindAddUserService(
        addUserServiceImpl: AddUserServiceImpl
    ): AddUserService
}