package com.thepop.android.di

import com.thepop.android.data.repository.CommunityRepositoryImpl
import com.thepop.android.data.repository.PopupRepositoryImpl
import com.thepop.android.data.repository.UserRepositoryImpl
import com.thepop.android.data.service.CommunityService
import com.thepop.android.data.service.PopupService
import com.thepop.android.data.service.UserService
import com.thepop.android.data.source.remote.popup.PopupDataSourceImpl
import com.thepop.android.data.source.remote.user.UserDataSourceImpl
import com.thepop.android.domain.repository.CommunityRepository
import com.thepop.android.domain.repository.PopupRepository
import com.thepop.android.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService): UserRepository =
        UserRepositoryImpl(userService)

    @Provides
    @Singleton
    fun providePopupRepository(popupService: PopupService): PopupRepository =
        PopupRepositoryImpl(popupService)

    @Provides
    @Singleton
    fun provideCommunityRepository(communityService: CommunityService): CommunityRepository =
        CommunityRepositoryImpl(communityService)

}