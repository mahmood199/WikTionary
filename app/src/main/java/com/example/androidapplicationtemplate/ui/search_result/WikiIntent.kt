package com.example.androidapplicationtemplate.ui.search_result

sealed class WikiIntent {
    data class GetPaginatedResult(
        val lastItemPosition: Int,
        val rvItemCount: Int,
        val tCount: Int,
    ) : WikiIntent()
    object GetInitialData : WikiIntent()
}