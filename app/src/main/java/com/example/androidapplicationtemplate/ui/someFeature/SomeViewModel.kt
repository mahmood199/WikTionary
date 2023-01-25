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
class SomeViewModel @Inject constructor(
	private val someUseCase: SomeUseCase,
	private val wikiUseCase: GetWikiUseCase
) : ViewModel() {

	init {
	    receiveIntents()
	}

	val intents: Channel<SomeIntent> =
		Channel(Channel.UNLIMITED)

	private val _state = MutableStateFlow<SomeState>(SomeState.Idle)
	val state: StateFlow<SomeState>
		get() = _state

	private val _effect = Channel<SomeEffect>()
	val effect: Flow<SomeEffect>
		get() = _effect.receiveAsFlow()


	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					SomeIntent.Intent1 -> doOperation1()
					SomeIntent.Intent2 -> doOperation2()
					SomeIntent.Intent3 -> doOperation3()
					SomeIntent.Intent4 -> doOperation4()
				}
			}
		}
	}

	private fun doOperation1() {
		viewModelScope.launch {
			_state.value = SomeState.Loading
			val result = wikiUseCase.invoke().collect {
				when(it) {
					is Resource.Failure -> {
						_state.value = SomeState.Error(it.failureStatus, it.message)
					}
					Resource.Loading -> {
						_state.value = SomeState.Loading
					}
					is Resource.Success -> {
						_state.value = SomeState.State1(it.value.toString())
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