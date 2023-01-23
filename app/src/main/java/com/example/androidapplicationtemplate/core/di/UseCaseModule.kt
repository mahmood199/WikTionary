package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.domain.repository.SomeRepository
import com.example.androidapplicationtemplate.domain.repository.WikiRepository
import com.example.androidapplicationtemplate.domain.usecase.GetWikiUseCase
import com.example.androidapplicationtemplate.domain.usecase.SomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

	@Provides
	fun provideSomeUseCase(
		someRepository: SomeRepository,
	): SomeUseCase = SomeUseCase(someRepository)

	@Provides
	fun provideGetWikiUseCase(
		wikiRepository: WikiRepository
	) : GetWikiUseCase = GetWikiUseCase(wikiRepository)


}