package com.example.androidapplicationtemplate.data.local.localDataSource

import com.example.androidapplicationtemplate.data.local.dao.WikiDao
import javax.inject.Inject

class WikiLocalDataSource @Inject constructor(wikiDao: WikiDao) {

    suspend fun addAll() {

    }

}