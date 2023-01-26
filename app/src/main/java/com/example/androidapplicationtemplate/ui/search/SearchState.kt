package com.example.androidapplicationtemplate.ui.search

import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.Page

sealed class SearchState {
    object Idle : SearchState()
    data class SendResult(val pages: List<Page>) : SearchState()
    data class Error(val failureStatus: FailureStatus, val message: String?) : SearchState()
    data class SendPaginatedResult(val pages: List<Page>) : SearchState()
    object Loading : SearchState()

}