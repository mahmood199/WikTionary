package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.remote.remoteServices.SomeService
import com.example.androidapplicationtemplate.data.remote.remoteServices.WikiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideSomeService(
        retrofit: Retrofit
    ): SomeService {
        return retrofit.create(SomeService::class.java)
    }

    @Provides
    fun providesWikiService(
        retrofit: Retrofit,
    ): WikiService = retrofit.create(WikiService::class.java)


}