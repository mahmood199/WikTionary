package com.example.androidapplicationtemplate.data.models.response

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pages")
class Page(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("pageid") val pageId: Int,
    @SerializedName("index") val index: Int,
    @SerializedName("ns") val ns: Int,
    @SerializedName("title") val title: String,
) {
    @Ignore
    @SerializedName("thumbnail") val thumbnail: Thumbnail? = null

}