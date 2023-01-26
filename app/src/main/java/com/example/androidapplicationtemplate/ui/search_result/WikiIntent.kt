package com.example.androidapplicationtemplate.ui.search_result

import android.content.Intent
import com.example.androidapplicationtemplate.data.models.response.Page

sealed class WikiIntent {
    data class GetPaginatedResult(
        val lastItemPosition: Int,
        val rvItemCount: Int,
        val tCount: Int,
    ) : WikiIntent()
    data class GetArgs(val intent: Intent) : WikiIntent()
    data class ShowDetails(val page: Page) : WikiIntent()
    object GetInitialData : WikiIntent()
    object LogSearches : WikiIntent()
}