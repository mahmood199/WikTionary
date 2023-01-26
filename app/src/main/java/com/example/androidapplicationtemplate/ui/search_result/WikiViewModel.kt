package com.example.androidapplicationtemplate.ui.search_result

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.models.entity.PagingEntity
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.domain.usecase.AllowPaginationUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetWikiUseCase
import com.example.androidapplicationtemplate.domain.usecase.SomeUseCase
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WikiViewModel @Inject constructor(
	private val someUseCase: SomeUseCase,
	private val wikiUseCase: GetWikiUseCase,
	private val allowPaginationUseCase: AllowPaginationUseCase
) : ViewModel() {

	companion object {
		const val LIMIT = 100
	}

	val intents: Channel<WikiIntent> =
		Channel(Channel.UNLIMITED)

	private val _state = MutableStateFlow<WikiState>(WikiState.Idle)
	val state: StateFlow<WikiState>
		get() = _state

	private val _effect = Channel<WikiEffect>()
	val effect: Flow<WikiEffect>
		get() = _effect.receiveAsFlow()

	var pageMutableList = mutableListOf<Page>()
	var searchedQuery = ""
	var offset = 0
	val limit = 15

	init {
		receiveIntents()
	}

	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					WikiIntent.GetInitialData -> getWikis(0)
					is WikiIntent.GetPaginatedResult -> {
						fetchPaginatedData(it.lastItemPosition, it.rvItemCount, it.tCount)
					}
					is WikiIntent.GetArgs -> {
						getArgs(it.intent)
					}
					WikiIntent.LogSearches -> {

					}
					is WikiIntent.ShowDetails -> {
						redirectToWikiDetailActivity(it.page)
					}
				}
			}
		}
	}

	private fun redirectToWikiDetailActivity(page: Page) {
		viewModelScope.launch {
			_effect.send(WikiEffect.NavigateToWikiDetailScreen(page))
		}
	}

	private fun getArgs(intent: Intent) {
		searchedQuery = intent.extras?.getString(BundleKeyIdentifier.SEARCH_QUERY) ?: ""
		_state.value = WikiState.ArgumentsReceived(searchedQuery)
	}

	private fun fetchPaginatedData(
		lastItemPosition: Int,
		recyclerViewItemsCount: Int,
		totalCount: Int
	) {
		try {
			val result = allowPaginationUseCase(
				PagingEntity(
					lastItemPosition = lastItemPosition,
					rvItemCount = recyclerViewItemsCount,
					tCount = totalCount,
					dataRequested = false
				)
			)

			when (result) {
				is Resource.Success -> {
					if (result.value) {
						offset += limit
						getWikis(offset)
					}
				} else -> {

				}
			}
		} catch (e: Exception) {
			e.printStackTrace()
			_state.value = WikiState.Error(
				FailureStatus.OTHER,
				e.localizedMessage
			)
		}
	}
	private fun getWikis(i: Int) {
		viewModelScope.launch {
			val result = wikiUseCase.invoke(searchedQuery).collect {
				when(it) {
					is Resource.Failure -> {
						_state.value = WikiState.Error(it.failureStatus, it.message)
					}
					Resource.Loading -> {
						if(i == 0)
							_state.value = WikiState.ShowShimmer
						else
							_state.value = WikiState.ShowLoader
					}
					is Resource.Success -> {
						delay(1000) // to keep some lag to show progress bar or shimmer
						if(it.value.didNetworkRequestFailed)
							_effect.send(WikiEffect.NoInternetAlert)
						if(i  == 0)
							_state.value = WikiState.SendResult(it.value.query.pages)
						else
							_state.value = WikiState.SendPaginatedResult(it.value.query.pages)
					}
					else -> {}
				}
			}
		}
	}


}