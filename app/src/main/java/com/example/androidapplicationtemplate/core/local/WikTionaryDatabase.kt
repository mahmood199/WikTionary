package com.example.androidapplicationtemplate.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidapplicationtemplate.data.local.dao.WikiDao
import com.example.androidapplicationtemplate.data.models.entity.LocalEntity
import com.example.androidapplicationtemplate.data.models.response.Page

@Database(entities = [LocalEntity::class, Page::class], version = 1)
abstract class WikTionaryDatabase : RoomDatabase() {

    abstract fun wikiDao() : WikiDao

}