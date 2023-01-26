package com.example.androidapplicationtemplate.ui.search

import android.text.Editable

sealed class SearchIntent {
    data class GetPaginatedResult(
        val lastItemPosition: Int,
        val rvItemCount: Int,
        val tCount: Int,
    ) : SearchIntent()

    object GetInitialData : SearchIntent()
    data class GoToSearchResultPage(val searchQuery: Editable?) : SearchIntent()
    data class GetSearchSuggestion(val it: Editable?) : SearchIntent()
}