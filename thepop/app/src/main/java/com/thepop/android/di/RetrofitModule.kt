package com.thepop.android.di

import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thepop.android.BuildConfig
import com.thepop.android.data.source.LocalDataSource
import com.thepop.android.ui.splash.SplashActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        @ApplicationContext context: Context,
        localDataSource: LocalDataSource
    ): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsc21sZWU5OUBnbWFpbC5jb20iLCJpYXQiOjE3MDg3OTA0MjIsImV4cCI6MTcwODg3NjgyMiwic3ViIjoidGVzdDEyMzNAZ21haWwuY29tIiwiaWQiOjF9.9w6859ga40pxal-GLORzfUcI4qDRvXVeqqvyqz-_clY")
                .build()
            val response = chain.proceed(request)
            if (response.code == 401) {
                handel401Error(context, localDataSource)
            }
            response
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun handel401Error(context: Context, localDataSource: LocalDataSource) {
        localDataSource.removeAccessToken()
        localDataSource.setAccessToken(null)
        val intent = Intent(context, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

}
