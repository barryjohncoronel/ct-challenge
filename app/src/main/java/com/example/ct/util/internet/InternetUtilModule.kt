package com.example.ct.util.internet

import dagger.Binds
import dagger.Module

@Module
interface InternetUtilModule {

    @Binds
    fun bindInternetUtil(
        internetUtilImpl: InternetUtilImpl
    ): InternetUtil
}