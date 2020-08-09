package com.example.cartrack.data.service.users

import com.example.cartrack.util.internet.InternetUtilModule
import dagger.Binds
import dagger.Module

@Module(includes = [InternetUtilModule::class])
interface UsersServiceModule {

    @Binds
    fun bindUsersService(
        usersServiceImpl: UsersServiceImpl
    ): UsersService
}