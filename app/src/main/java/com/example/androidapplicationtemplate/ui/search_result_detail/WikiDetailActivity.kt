package com.example.androidapplicationtemplate.ui.search_result_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.databinding.ActivityWikiDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WikiDetailActivity : AppCompatActivity() {

    companion object {
        const val CROSSFADE_ANIMATION_TIME_DURATION = 500
    }

    private lateinit var binding: ActivityWikiDetailBinding
    private val viewModel: WikiDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViews()
        setObservers()
        getArgs(intent)
    }

    private fun setUpViews() {
        binding = ActivityWikiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
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

    private fun setUIState(it: WikiDetailState) {
        when (it) {
            WikiDetailState.Idle -> {}
            is WikiDetailState.ShowPageData -> {
                showDetailsOnViews(it.page)
            }
        }
    }

    private fun showDetailsOnViews(page: Page) {
        binding.apply {
            tvField1.text = page.title
            tvField2.text = page.index.toString()
            tvField3.text = page.pageId.toString()

            Glide.with(root.context)
                .load(page.thumbnail?.source ?: "")
                .centerCrop()
                .placeholder(ContextCompat.getDrawable(root.context, R.drawable.place_holder))
                .transition(DrawableTransitionOptions.withCrossFade(
                    CROSSFADE_ANIMATION_TIME_DURATION))
                .into(ivWikiThumbnail)
        }
    }

    private fun setUIEffect(it: WikiDetailEffect) {
        when (it) {
            else -> {}
        }
    }

    private fun getArgs(intent: Intent?) {
        triggerAction(WikiDetailIntent.GetArgs(intent))
    }

    private fun setListeners() {
        binding.apply {

        }
    }

    private fun triggerAction(it: WikiDetailIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(it)
        }
    }

}