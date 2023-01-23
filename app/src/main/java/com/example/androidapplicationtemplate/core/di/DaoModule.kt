package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.core.local.WikTionaryDatabase
import com.example.androidapplicationtemplate.data.local.dao.SomeDao
import com.example.androidapplicationtemplate.data.local.dao.WikiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

	@Provides
	fun providesSomeDao(): SomeDao {
		return SomeDao()
	}

	@Provides
	fun providesWikiDao(wikTionaryDatabase: WikTionaryDatabase): WikiDao {
		return wikTionaryDatabase.wikiDao()
	}

}