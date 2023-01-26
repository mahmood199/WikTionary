package com.example.androidapplicationtemplate.ui.search_result

import com.example.androidapplicationtemplate.data.models.response.Page

sealed class WikiEffect {
    data class NavigateToWikiDetailScreen(val page: Page) : WikiEffect()
    object NoInternetAlert : WikiEffect()
}