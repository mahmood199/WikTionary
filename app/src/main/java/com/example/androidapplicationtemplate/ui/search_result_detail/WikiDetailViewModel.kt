package com.example.androidapplicationtemplate.ui.search_result_detail

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WikiDetailViewModel @Inject constructor(

) : ViewModel() {

    companion object {
        const val INVALID_PAGE_ID = -1
        const val INVALID_PAGE_INDEX = -1
        const val INVALID_PAGE_NS = -1
        const val INVALID_PAGE_THUMBNAIL = ""
    }

    val intents: Channel<WikiDetailIntent> =
        Channel(Channel.UNLIMITED)

    private val _state = MutableStateFlow<WikiDetailState>(WikiDetailState.Idle)
    val state: StateFlow<WikiDetailState>
        get() = _state

    private val _effect = Channel<WikiDetailEffect>()
    val effect: Flow<WikiDetailEffect>
        get() = _effect.receiveAsFlow()

    init {
        receiveIntents()
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when (it) {
                    is WikiDetailIntent.GetArgs -> {
                        processArgs(it.intent)
                    }
                }
            }
        }
    }

    private fun processArgs(intent: Intent?) {
        val args = intent?.extras?.getParcelable(BundleKeyIdentifier.WIKI_DATA) ?: Page(
            INVALID_PAGE_ID,
            INVALID_PAGE_INDEX,
            INVALID_PAGE_NS,
            INVALID_PAGE_THUMBNAIL)
        _state.value = WikiDetailState.ShowPageData(args)
    }

}