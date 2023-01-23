package com.example.androidapplicationtemplate.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "local_entity")
data class LocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "some_value")
    val x: Int,
)