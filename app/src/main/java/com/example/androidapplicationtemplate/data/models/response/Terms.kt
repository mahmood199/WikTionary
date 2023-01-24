package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Terms(
    @SerializedName("description") val description: List<String>,
)
