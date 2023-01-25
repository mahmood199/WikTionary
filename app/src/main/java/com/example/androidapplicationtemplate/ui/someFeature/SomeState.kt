package com.example.androidapplicationtemplate.ui.someFeature

import com.example.androidapplicationtemplate.core.util.FailureStatus

sealed class SomeState {
    object Idle : SomeState()
    data class State1(val message: String) : SomeState()
    data class Error(val failureStatus: FailureStatus, val message: String?) : SomeState()
    object State2 : SomeState()
    object State3 : SomeState()
    object State4 : SomeState()
    object Loading : SomeState()

}