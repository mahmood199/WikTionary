package com.example.androidapplicationtemplate.ui.search

import android.text.Editable
import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                    is SearchIntent.GoToSearchResultPage -> redirectToSearchResultScreen(it.searchQuery)
                }
            }
        }
    }

    private fun redirectToSearchResultScreen(searchQuery: Editable?) {
        viewModelScope.launch {
            if(TextUtils.isEmpty(searchQuery)) {
                _effect.send(SearchEffect.BlankSearchQuery)
            }
            else {
                _effect.send(SearchEffect.NavigateToSearchResultPage(searchQuery.toString()))
            }
        }
    }


}