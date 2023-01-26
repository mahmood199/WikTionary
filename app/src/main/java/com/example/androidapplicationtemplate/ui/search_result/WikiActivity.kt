package com.example.androidapplicationtemplate.ui.search_result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.core.extension.makeGone
import com.example.androidapplicationtemplate.core.extension.makeVisible
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.databinding.ActivitySomeBinding
import com.example.androidapplicationtemplate.ui.search_result_detail.WikiDetailActivity
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WikiActivity : AppCompatActivity() {

	companion object {
		const val CROSSFADE_ANIMATION_TIME_DURATION = 500
	}

	private lateinit var binding : ActivitySomeBinding
	private val viewModel : WikiViewModel by viewModels()
	private val wikiAdapter by lazy {
		WikiAdapter {
			triggerAction(WikiIntent.ShowDetails(it))
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setUpViews()
		setObservers()
		getArgs(intent)
	}

	private fun setUpViews() {
		binding = ActivitySomeBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.rvWiki.makeGone()
		setListeners()
	}

	private fun setListeners() {
		binding.apply {
			getAllLocalWikis.setOnClickListener {
				triggerAction(WikiIntent.LogSearches)
			}
		}
		setRecyclerViewScrollListener()
	}

	private fun setRecyclerViewScrollListener() {
		binding.apply {
			rvWiki.adapter = wikiAdapter
			rvWiki.addOnScrollListener(object : RecyclerView.OnScrollListener() {

				override fun onScrollStateChanged(
					recyclerView: RecyclerView,
					newState: Int
				) {
					super.onScrollStateChanged(recyclerView, newState)

					val lastItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()

					val rvItemCount = rvWiki.adapter?.itemCount!!
					val tCount = WikiViewModel.LIMIT

					triggerAction(
						WikiIntent.GetPaginatedResult(
							lastItemPosition,
							rvItemCount,
							tCount
						)
					)
				}
			})
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

	private fun getArgs(intent: Intent) {
		triggerAction(WikiIntent.GetArgs(intent))
	}


	private fun setUIState(it: WikiState) {
		when(it) {
			WikiState.Idle -> {}
			is WikiState.SendResult -> {
				binding.cpiWiki.makeGone()
				hideShimmer()
				setDataOnRecyclerViewAdapter(it.pages)
			}
			is WikiState.SendPaginatedResult -> {
				binding.cpiWiki.makeGone()
				appendPagesToTheEndOfList(it.pages)
			}
			is WikiState.Error -> {
				hideShimmer()
				binding.cpiWiki.makeGone()
				SnackBarBuilder.getSnackbar(this,
                    it.message ?: getString(R.string.something_went_wrong),
                    Snackbar.LENGTH_SHORT).show()
			}
			is WikiState.ArgumentsReceived -> {
				triggerAction(WikiIntent.GetInitialData)
			}
			WikiState.ShowLoader -> {
				binding.cpiWiki.apply {
					makeVisible()
				}
			}
			WikiState.ShowShimmer -> {
				showAnimatingShimmer()
			}
		}
	}

	private fun showAnimatingShimmer() {
		binding.apply {
			rvWiki.makeGone()
			cpiWiki.makeGone()
			nsvSearchResult.makeVisible()
			sflSearchResult.showShimmer(true)
			sflSearchResult.startShimmer()
		}
	}

	private fun hideShimmer() {
		binding.apply {
			nsvSearchResult.makeGone()
			sflSearchResult.showShimmer(false)
			sflSearchResult.stopShimmer()
		}
	}

	private fun appendPagesToTheEndOfList(pages: List<Page>) {
		binding.rvWiki.apply {
			(adapter as WikiAdapter).addItems(pages.toMutableList())
		}
	}

	private fun setDataOnRecyclerViewAdapter(pages: List<Page>) {
		binding.rvWiki.apply {
			makeVisible()
			(adapter as WikiAdapter).addItems(pages.toMutableList())
		}
	}

	private fun setUIEffect(it: WikiEffect) {
		when(it) {
			is WikiEffect.NavigateToWikiDetailScreen -> {
				navigateToWikiDetailActivity(it.page)
			}
			WikiEffect.NoInternetAlert -> {
				SnackBarBuilder.getSnackbar(
                    this,
                    getString(R.string.no_internet_message),
                    Snackbar.LENGTH_SHORT)
                    .show()
			}
		}
	}

	private fun navigateToWikiDetailActivity(page: Page) {
		startActivity(
            Intent(this, WikiDetailActivity::class.java)
                .putExtra(BundleKeyIdentifier.WIKI_DATA, page))
	}

	private fun triggerAction(it: WikiIntent) {
		lifecycleScope.launch {
			viewModel.intents.send(it)
		}
	}


}