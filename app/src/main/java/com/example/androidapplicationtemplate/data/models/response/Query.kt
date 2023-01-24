package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName


data class Query(
    @SerializedName("pages") val pages: List<Page>,
    @SerializedName("redirects") val redirects: List<Redirect>,
)