package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("index") val index: Int,
    @SerializedName("ns") val ns: Int,
    @SerializedName("pageid") val pageId: Int,
    @SerializedName("terms") val terms: Terms,
    @SerializedName("thumbnail") val thumbnail: Thumbnail? = null,
    @SerializedName("title") val title: String,
)
