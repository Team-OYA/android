package com.thepop.android.di

import com.thepop.android.data.service.BusinessService
import com.thepop.android.data.service.CommunityService
import com.thepop.android.data.service.PopupService
import com.thepop.android.data.service.UserService
import com.thepop.android.data.source.LocalDataSource
import com.thepop.android.data.source.LocalDataSourceImpl
import com.thepop.android.data.source.remote.business.BusinessDataSource
import com.thepop.android.data.source.remote.business.BusinessDataSourceImpl
import com.thepop.android.data.source.remote.community.CommunityDataSource
import com.thepop.android.data.source.remote.community.CommunityDataSourceImpl
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

    @Provides
    @Singleton
    fun provideUserService(service: CommunityService): CommunityDataSource =
        CommunityDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideBusinessService(service: BusinessService): BusinessDataSource =
        BusinessDataSourceImpl(service)

}
