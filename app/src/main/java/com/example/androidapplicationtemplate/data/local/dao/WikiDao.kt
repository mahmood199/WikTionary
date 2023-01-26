package com.example.androidapplicationtemplate.data.local.dao

import androidx.room.*
import com.example.androidapplicationtemplate.data.models.response.Page

@Dao
interface WikiDao {

    @Query("SELECT * FROM pages WHERE title LIKE '%' || :searchedQuery || '%' ORDER BY pageId ASC")
    suspend fun getWikis(searchedQuery : String) : List<Page>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAllWikis(list: List<Page>) : LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWiki(page: Page) : Long

    @Delete
    suspend fun deleteWiki(page: Page)

}