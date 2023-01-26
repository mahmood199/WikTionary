package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName


data class Query(
    @SerializedName("pages") val pages: List<Page> = listOf(),
    @SerializedName("redirects") val redirects: List<Redirect> = listOf(),
)