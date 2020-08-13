package com.example.cartrack.data.service.local

import com.example.cartrack.data.service.local.loader.LocalUserCredentialsLoader
import com.example.cartrack.data.service.local.loader.LocalUserCredentialsLoaderImpl
import com.example.cartrack.data.service.local.saver.LocalUserCredentialsSaver
import com.example.cartrack.data.service.local.saver.LocalUserCredentialsCredentialsSaverImpl
import dagger.Binds
import dagger.Module

@Module
interface LocalUserCredentialsModule {

    @Binds
    fun bindLocalUserCredentialsLoaderImpl(localUserCredentialsLoaderImpl: LocalUserCredentialsLoaderImpl):
            LocalUserCredentialsLoader

    @Binds
    fun bindLocalUserCredentialsSaverImpl(localUserCredentialsSaverImpl: LocalUserCredentialsCredentialsSaverImpl):
            LocalUserCredentialsSaver
}