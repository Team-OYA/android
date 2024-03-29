package com.thepop.android.di

import com.thepop.android.data.service.BusinessService
import com.thepop.android.data.service.CommunityService
import com.thepop.android.data.service.PopupService
import com.thepop.android.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providePopupService(retrofit: Retrofit): PopupService =
        retrofit.create(PopupService::class.java)

    @Provides
    @Singleton
    fun provideCommunityService(retrofit: Retrofit): CommunityService =
        retrofit.create(CommunityService::class.java)

    @Provides
    @Singleton
    fun provideBusinessService(retrofit: Retrofit): BusinessService =
        retrofit.create(BusinessService::class.java)

}