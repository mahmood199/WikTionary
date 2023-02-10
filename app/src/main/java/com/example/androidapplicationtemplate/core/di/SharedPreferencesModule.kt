package com.example.androidapplicationtemplate.core.di

import android.content.Context
import com.example.androidapplicationtemplate.core.local.ApplicationPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context) = ApplicationPreferences(context)

}
