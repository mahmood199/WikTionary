package com.example.androidapplicationtemplate.ui.someFeature

import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.Page

sealed class SomeState {
    object Idle : SomeState()
    data class State1(val pages: List<Page>) : SomeState()
    data class Error(val failureStatus: FailureStatus, val message: String?) : SomeState()
    object State2 : SomeState()
    object State3 : SomeState()
    object State4 : SomeState()
    object Loading : SomeState()

}