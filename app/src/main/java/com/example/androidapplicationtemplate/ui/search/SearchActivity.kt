package com.example.androidapplicationtemplate.ui.search

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.databinding.ActivitySearchBinding
import com.example.androidapplicationtemplate.ui.search_result.WikiActivity
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySearchBinding
	private val viewModel : SearchViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setUpViews()
		setObservers()
	}

	private fun setUpViews() {
		binding = ActivitySearchBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setClickListeners()
	}

	private fun setClickListeners() {
		binding.apply {
			btnGoDifferentScreen.setOnClickListener {
				triggerAction(SearchIntent.GoToSearchResultPage(etSearch.text))
			}
		}
	}

	private fun setObservers() {
		lifecycleScope.launch {
			viewModel.state.collect {
				setUIState(it)
			}
		}

		lifecycleScope.launch {
			viewModel.effect.collect {
				setUIEffect(it)
			}
		}
	}

	private fun setUIState(it: SearchState) {
		when(it) {
			is SearchState.Error -> {}
			SearchState.Idle -> {}
			SearchState.Loading -> {}
			is SearchState.SendPaginatedResult -> {}
			is SearchState.SendResult -> {}
		}
	}

	private fun setUIEffect(it: SearchEffect) {
		when(it) {
			SearchEffect.Effect1 -> {}
			SearchEffect.Effect2 -> {}
			SearchEffect.Effect3 -> {}
			SearchEffect.Effect4 -> {}
			is SearchEffect.NavigateToSearchResultPage -> {
				startActivity(
                    Intent(this, WikiActivity::class.java)
                        .putExtra(BundleKeyIdentifier.SEARCH_QUERY, it.searchQuery))
			}
			SearchEffect.BlankSearchQuery -> {
				Toast.makeText(this, getString(R.string.blank_search_query_message), Toast.LENGTH_SHORT).show()
			}
		}
	}


	private fun triggerAction(it: SearchIntent) {
		lifecycleScope.launch {
			viewModel.intents.send(it)
		}
	}


}