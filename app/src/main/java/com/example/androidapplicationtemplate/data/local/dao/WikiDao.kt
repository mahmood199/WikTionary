package com.example.androidapplicationtemplate.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.androidapplicationtemplate.data.models.entity.LocalEntity

@Dao
interface WikiDao {

    @Query("select * from local_entity")
    suspend fun getWikis() : List<LocalEntity>

}