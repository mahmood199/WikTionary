package com.example.androidapplicationtemplate.ui.someFeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.core.extension.makeGone
import com.example.androidapplicationtemplate.core.extension.makeVisible
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.databinding.ActivitySomeBinding
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SomeActivity : AppCompatActivity() {

	private lateinit var binding : ActivitySomeBinding
	private val someViewModel : SomeViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivitySomeBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setObservers()
		binding.rvWiki.makeGone()
		triggerAction(SomeIntent.Intent1)
	}

	private fun setObservers() {
		lifecycleScope.launch {
			someViewModel.state.collect {
				setUIState(it)
			}
		}

		lifecycleScope.launch {
			someViewModel.effect.collect {
				setUIEffect(it)
			}
		}
	}

	private fun setUIState(it: SomeState) {
		when(it) {
			SomeState.Idle -> {}
			is SomeState.State1 -> {
				binding.cpiWiki.makeGone()
				setDataOnRecyclerViewAdapter(it.pages)
			}
			SomeState.State2 -> {

			}
			SomeState.State3 -> {

			}
			SomeState.State4 -> {

			}
			SomeState.Loading -> {
				binding.cpiWiki.apply {

					makeVisible()
				}
			}
			is SomeState.Error -> {
				SnackBarBuilder.getSnackbar(this,
                    it.message ?: getString(R.string.something_went_wrong),
                    Snackbar.LENGTH_SHORT)
			}
		}
	}

	private fun setDataOnRecyclerViewAdapter(pages: List<Page>) {
		binding.rvWiki.apply {
			makeVisible()
			adapter = WikiAdapter(pages.toMutableList())
		}
	}

	private fun setUIEffect(it: SomeEffect) {
		when(it) {
			SomeEffect.Effect1 -> TODO()
			SomeEffect.Effect2 -> TODO()
			SomeEffect.Effect3 -> TODO()
			SomeEffect.Effect4 -> TODO()
		}
	}

	private fun triggerAction(it: SomeIntent) {
		lifecycleScope.launch {
			someViewModel.intents.send(it)
		}
	}


}