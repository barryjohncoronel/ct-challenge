package com.example.cartrack.data.db

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserCredentialsDao(appDatabase: AppDatabase): UserCredentialsDao {
        return appDatabase.userCredentialsDao()
    }

}