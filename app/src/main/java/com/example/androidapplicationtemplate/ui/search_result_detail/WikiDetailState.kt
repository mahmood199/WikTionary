package com.example.androidapplicationtemplate.ui.search_result_detail

import com.example.androidapplicationtemplate.data.models.response.Page

sealed class WikiDetailState {
    object Idle : WikiDetailState()
    data class ShowPageData(val page: Page) : WikiDetailState()

}