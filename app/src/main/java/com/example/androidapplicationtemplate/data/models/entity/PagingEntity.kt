package com.example.androidapplicationtemplate.data.models.entity

data class PagingEntity(
    val lastItemPosition: Int,
    val rvItemCount: Int,
    val tCount: Int,
    val dataRequested: Boolean
)
