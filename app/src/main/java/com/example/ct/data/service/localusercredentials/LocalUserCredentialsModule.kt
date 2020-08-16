package com.example.ct.data.service.localusercredentials

import com.example.ct.data.service.localusercredentials.loader.LocalUserCredentialsLoader
import com.example.ct.data.service.localusercredentials.loader.LocalUserCredentialsLoaderImpl
import com.example.ct.data.service.localusercredentials.saver.LocalUserCredentialsSaver
import com.example.ct.data.service.localusercredentials.saver.LocalUseCredentialsSaverImpl
import dagger.Binds
import dagger.Module

@Module
interface LocalUserCredentialsModule {

    @Binds
    fun bindLocalUserCredentialsLoaderImpl(localUserCredentialsLoaderImpl: LocalUserCredentialsLoaderImpl):
            LocalUserCredentialsLoader

    @Binds
    fun bindLocalUserCredentialsSaverImpl(localUserCredentialsSaverImpl: LocalUseCredentialsSaverImpl):
            LocalUserCredentialsSaver
}