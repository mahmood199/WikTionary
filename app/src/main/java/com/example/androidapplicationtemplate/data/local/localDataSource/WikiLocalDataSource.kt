package com.example.androidapplicationtemplate.data.local.localDataSource

import com.example.androidapplicationtemplate.data.local.dao.WikiDao
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.data.models.response.Response
import javax.inject.Inject

class WikiLocalDataSource @Inject constructor(val wikiDao: WikiDao) {

    suspend fun addAll(result: Response): LongArray {
        return wikiDao.insertAllWikis(result.query.pages)
    }

    suspend fun getAll(searchQuery: String) : List<Page> {
        return wikiDao.getWikis(searchQuery)
    }

}