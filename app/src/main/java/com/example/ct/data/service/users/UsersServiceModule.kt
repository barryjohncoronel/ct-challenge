package com.example.ct.data.service.users

import com.example.ct.util.internet.InternetUtilModule
import dagger.Binds
import dagger.Module

@Module(includes = [InternetUtilModule::class])
interface UsersServiceModule {

    @Binds
    fun bindUsersService(
        usersServiceImpl: UsersServiceImpl
    ): UsersService
}