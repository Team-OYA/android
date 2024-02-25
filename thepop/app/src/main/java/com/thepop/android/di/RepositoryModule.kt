package com.thepop.android.di

import com.thepop.android.data.repository.UserRepositoryImpl
import com.thepop.android.data.source.remote.UserDataSourceImpl
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
    fun provideUserRepository(userDataSourceImpl: UserDataSourceImpl): UserRepository =
        UserRepositoryImpl(userDataSourceImpl)

}