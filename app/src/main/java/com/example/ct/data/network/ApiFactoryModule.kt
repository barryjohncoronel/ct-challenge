package com.example.ct.data.network

import dagger.Binds
import dagger.Module

@Module
interface ApiFactoryModule {

    @Binds
    fun bindApiFactory(apiFactoryImpl: ApiFactoryImpl): ApiFactory
}