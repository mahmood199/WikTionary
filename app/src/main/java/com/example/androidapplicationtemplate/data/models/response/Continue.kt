package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Continue(
    @SerializedName("gpsoffset")
    val gpsOffset: Int = 0,
)