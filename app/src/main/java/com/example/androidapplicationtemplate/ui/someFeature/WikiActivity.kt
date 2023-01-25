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
class WikiActivity : AppCompatActivity() {

	private lateinit var binding : ActivitySomeBinding
	private val wikiViewModel : WikiViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivitySomeBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setObservers()
		binding.rvWiki.makeGone()
		triggerAction(WikiIntent.Intent1)
	}

	private fun setObservers() {
		lifecycleScope.launch {
			wikiViewModel.state.collect {
				setUIState(it)
			}
		}

		lifecycleScope.launch {
			wikiViewModel.effect.collect {
				setUIEffect(it)
			}
		}
	}

	private fun setUIState(it: WikiState) {
		when(it) {
			WikiState.Idle -> {}
			is WikiState.State1 -> {
				binding.cpiWiki.makeGone()
				setDataOnRecyclerViewAdapter(it.pages)
			}
			WikiState.State2 -> {

			}
			WikiState.State3 -> {

			}
			WikiState.State4 -> {

			}
			WikiState.Loading -> {
				binding.cpiWiki.apply {

					makeVisible()
				}
			}
			is WikiState.Error -> {
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

	private fun setUIEffect(it: WikiEffect) {
		when(it) {
			WikiEffect.Effect1 -> TODO()
			WikiEffect.Effect2 -> TODO()
			WikiEffect.Effect3 -> TODO()
			WikiEffect.Effect4 -> TODO()
		}
	}

	private fun triggerAction(it: WikiIntent) {
		lifecycleScope.launch {
			wikiViewModel.intents.send(it)
		}
	}


}