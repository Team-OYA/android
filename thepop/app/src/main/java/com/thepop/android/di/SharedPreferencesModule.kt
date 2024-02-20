package com.thepop.android.di

import com.thepop.android.data.local.AuthLocalPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideAuthLocalPreferencesImpl() = AuthLocalPreferences

}
