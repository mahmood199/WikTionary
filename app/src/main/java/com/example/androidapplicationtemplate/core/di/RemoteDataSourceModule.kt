package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.remote.remoteDataSource.SomeRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.WikiRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteServices.SomeService
import com.example.androidapplicationtemplate.data.remote.remoteServices.WikiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

	@Provides
	fun provideRemoteDataSource(
		someService: SomeService,
	): SomeRemoteDataSource {
		return SomeRemoteDataSource(someService)
	}

	@Provides
	fun provideWikiRemoteDataSource(
		wikiService: WikiService
	): WikiRemoteDataSource {
		return WikiRemoteDataSource(wikiService)
	}

}