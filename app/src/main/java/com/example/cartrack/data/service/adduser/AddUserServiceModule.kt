package com.example.cartrack.data.service.adduser

import com.example.cartrack.data.service.adduser.AddUserService
import com.example.cartrack.data.service.adduser.AddUserServiceImpl
import dagger.Binds
import dagger.Module

@Module
interface AddUserServiceModule {

    @Binds
    fun bindAddUserService(
        addUserServiceImpl: AddUserServiceImpl
    ): AddUserService
}