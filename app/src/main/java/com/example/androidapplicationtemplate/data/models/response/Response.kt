package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("batchcomplete") val batchComplete: Boolean,
    @SerializedName("continue") val cont: Continue,
    @SerializedName("query") val query: Query,
)