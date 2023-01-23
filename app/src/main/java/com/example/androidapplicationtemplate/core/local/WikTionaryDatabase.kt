package com.example.androidapplicationtemplate.core.local

import androidx.room.RoomDatabase
import com.example.androidapplicationtemplate.data.local.dao.WikiDao


abstract class WikTionaryDatabase : RoomDatabase() {

    abstract fun wikiDao() : WikiDao

}