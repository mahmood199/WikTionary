package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("height") val height: Int,
    @SerializedName("source") val source: String? = null,
    @SerializedName("width") val width: Int,
)