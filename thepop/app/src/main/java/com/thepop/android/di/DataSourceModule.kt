package com.thepop.android.di

import com.thepop.android.data.service.UserService
import com.thepop.android.data.source.LocalDataSource
import com.thepop.android.data.source.LocalDataSourceImpl
import com.thepop.android.data.source.remote.UserDataSource
import com.thepop.android.data.source.remote.UserDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl):
            LocalDataSource = localDataSourceImpl

    @Provides
    @Singleton
    fun provideUserDataSource(service: UserService): UserDataSource =
        UserDataSourceImpl(service)

}