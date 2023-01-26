package com.example.androidapplicationtemplate.ui.search_result

import android.content.Intent

sealed class WikiIntent {
    data class GetPaginatedResult(
        val lastItemPosition: Int,
        val rvItemCount: Int,
        val tCount: Int,
    ) : WikiIntent()
    data class GetArgs(val intent: Intent) : WikiIntent()
    object GetInitialData : WikiIntent()
    object LogSearches : WikiIntent()
}