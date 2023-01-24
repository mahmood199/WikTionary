package com.example.androidapplicationtemplate.ui.someFeature

sealed class SomeState {
    object Idle : SomeState()
    data class State1(val message: String) : SomeState()
    object State2 : SomeState()
    object State3 : SomeState()
    object State4 : SomeState()
    object Loading : SomeState()

}