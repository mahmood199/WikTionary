package com.example.androidapplicationtemplate.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.ui.search_result.WikiEffect
import com.example.androidapplicationtemplate.ui.search_result.WikiIntent
import com.example.androidapplicationtemplate.ui.search_result.WikiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

    val intents: Channel<SearchIntent> =
        Channel(Channel.UNLIMITED)

    private val _state = MutableStateFlow<SearchState>(SearchState.Idle)
    val state: StateFlow<SearchState>
        get() = _state

    private val _effect = Channel<SearchEffect>()
    val effect: Flow<SearchEffect>
        get() = _effect.receiveAsFlow()

    init {
        receiveIntents()
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when(it) {
                    SearchIntent.GetInitialData -> {}
                    is SearchIntent.GetPaginatedResult -> {}
                }
            }
        }
    }


}