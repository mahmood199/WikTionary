package com.example.androidapplicationtemplate.ui.search

sealed class SearchIntent {
    data class GetPaginatedResult(
        val lastItemPosition: Int,
        val rvItemCount: Int,
        val tCount: Int,
    ) : SearchIntent()
    object GetInitialData : SearchIntent()
}