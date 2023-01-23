package com.example.androidapplicationtemplate.core.di

import android.content.Context
import androidx.room.Room
import com.example.androidapplicationtemplate.core.local.WikTionaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    const val DB_NAME = "wiki_db"

    @Provides
    @Singleton
    fun provideWikiDatabase(@ApplicationContext context: Context) : WikTionaryDatabase {
        return Room.databaseBuilder(
            context,
            WikTionaryDatabase::class.java,
            DB_NAME)
            .build()
    }

}