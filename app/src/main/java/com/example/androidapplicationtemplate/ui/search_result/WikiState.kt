package com.example.androidapplicationtemplate.ui.search_result

import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.Page

sealed class WikiState {
    object Idle : WikiState()
    data class SendResult(val pages: List<Page>) : WikiState()
    data class Error(val failureStatus: FailureStatus, val message: String?) : WikiState()
    data class SendPaginatedResult(val pages: List<Page>) : WikiState()
    object Loading : WikiState()

}