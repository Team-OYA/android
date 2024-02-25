package com.thepop.android.di

import com.thepop.android.data.service.PopupService
import com.thepop.android.data.service.UserService
import com.thepop.android.data.source.LocalDataSource
import com.thepop.android.data.source.LocalDataSourceImpl
import com.thepop.android.data.source.remote.popup.PopupDataSource
import com.thepop.android.data.source.remote.popup.PopupDataSourceImpl
import com.thepop.android.data.source.remote.user.UserDataSource
import com.thepop.android.data.source.remote.user.UserDataSourceImpl
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

    @Provides
    @Singleton
    fun providePopupDataSource(service: PopupService): PopupDataSource =
        PopupDataSourceImpl(service)

}