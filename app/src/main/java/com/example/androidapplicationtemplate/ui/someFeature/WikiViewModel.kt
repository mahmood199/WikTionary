package com.example.androidapplicationtemplate.ui.someFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.util.Resource
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
	private val wikiUseCase: GetWikiUseCase
) : ViewModel() {

	init {
	    receiveIntents()
	}

	val intents: Channel<WikiIntent> =
		Channel(Channel.UNLIMITED)

	private val _state = MutableStateFlow<WikiState>(WikiState.Idle)
	val state: StateFlow<WikiState>
		get() = _state

	private val _effect = Channel<WikiEffect>()
	val effect: Flow<WikiEffect>
		get() = _effect.receiveAsFlow()


	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					WikiIntent.Intent1 -> doOperation1()
					WikiIntent.Intent2 -> doOperation2()
					WikiIntent.Intent3 -> doOperation3()
					WikiIntent.Intent4 -> doOperation4()
				}
			}
		}
	}

	private fun doOperation1() {
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
						_state.value = WikiState.State1(it.value.query.pages)
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