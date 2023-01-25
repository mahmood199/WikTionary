package com.example.androidapplicationtemplate.ui.someFeature

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
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WikiActivity : AppCompatActivity() {

	private lateinit var binding : ActivitySomeBinding
	private val wikiViewModel : WikiViewModel by viewModels()
	private val wikiAdapter by lazy {
		WikiAdapter(mutableListOf())
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivitySomeBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setObservers()
		setUpViews()
		triggerAction(WikiIntent.GetInitialData)
	}

	private fun setUpViews() {
		binding.rvWiki.makeGone()
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
			is WikiState.SendResult -> {
				binding.cpiWiki.makeGone()
				setDataOnRecyclerViewAdapter(it.pages)
			}
			is WikiState.SendPaginatedResult -> {
				appendPagesToTheEndOfList(it.pages)
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