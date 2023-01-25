package com.example.androidapplicationtemplate.ui.someFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.models.entity.PagingEntity
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.domain.usecase.AllowPaginationUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetWikiUseCase
import com.example.androidapplicationtemplate.domain.usecase.SomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
					WikiIntent.Intent2 -> doOperation2()
					WikiIntent.Intent3 -> doOperation3()
					WikiIntent.Intent4 -> doOperation4()
					is WikiIntent.GetPaginatedResult -> {
						fetchPaginatedData(it.lastItemPosition, it.rvItemCount, it.tCount)
					}
				}
			}
		}
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
			_state.value = WikiState.Loading
			val result = wikiUseCase.invoke().collect {
				when(it) {
					is Resource.Failure -> {
						_state.value = WikiState.Error(it.failureStatus, it.message)
					}
					Resource.Loading -> {
						_state.value = WikiState.Loading
					}
					is Resource.Success -> {
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

	private fun doOperation2() {
		TODO("Not yet implemented")
	}

	private fun doOperation3() {
		TODO("Not yet implemented")
	}

	private fun doOperation4() {
		TODO("Not yet implemented")
	}


}