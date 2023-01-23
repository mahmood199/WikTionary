package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.local.dao.SomeDao
import com.example.androidapplicationtemplate.data.local.dao.WikiDao
import com.example.androidapplicationtemplate.data.local.localDataSource.SomeLocalDataSource
import com.example.androidapplicationtemplate.data.local.localDataSource.WikiLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

	@Provides
	fun provideLocalDataSource(someDao: SomeDao): SomeLocalDataSource {
		return SomeLocalDataSource(someDao)
	}

	@Provides
	fun provideWikiLocalDataSource(wikiDao: WikiDao) : WikiLocalDataSource {
		return WikiLocalDataSource(wikiDao)
	}

}