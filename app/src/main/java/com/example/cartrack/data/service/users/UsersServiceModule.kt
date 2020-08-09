package com.example.cartrack.data.service.users

import dagger.Binds
import dagger.Module

@Module
interface UsersServiceModule {

    @Binds
    fun bindMainService(
        usersServiceImpl: UsersServiceImpl
    ): UsersService
}