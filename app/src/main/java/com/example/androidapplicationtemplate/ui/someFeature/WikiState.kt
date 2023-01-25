package com.example.androidapplicationtemplate.ui.someFeature

import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.Page

sealed class WikiState {
    object Idle : WikiState()
    data class State1(val pages: List<Page>) : WikiState()
    data class Error(val failureStatus: FailureStatus, val message: String?) : WikiState()
    object State2 : WikiState()
    object State3 : WikiState()
    object State4 : WikiState()
    object Loading : WikiState()

}