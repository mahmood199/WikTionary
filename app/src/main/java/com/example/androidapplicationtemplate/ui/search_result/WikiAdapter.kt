package com.example.androidapplicationtemplate.ui.search_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.data.models.response.Page
import com.example.androidapplicationtemplate.databinding.ItemWikiBinding

class WikiAdapter(
    private val listener : (Page) -> Unit
) : RecyclerView.Adapter<WikiAdapter.ViewHolder>() {

    private val pages: MutableList<Page> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWikiBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bindData(pages[position])
            setClickListener(pages[position])
        }
    }

    override fun getItemCount() = pages.size

    fun addItems(newPages: MutableList<Page>) {
        val size = this.pages.size
        this.pages.addAll(newPages)
        notifyItemRangeChanged(size, newPages.size)
    }

    inner class ViewHolder(private val binding: ItemWikiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(page: Page) {
            binding.apply {
                tvField1.text = page.title
                tvField2.text = page.pageId.toString()
                tvField3.text = page.index.toString()


                Glide.with(root)
                    .load(page.thumbnail?.source ?: "")
                    .placeholder(ContextCompat.getDrawable(root.context, R.drawable.place_holder))
                    .transition(DrawableTransitionOptions.withCrossFade(WikiActivity.CROSSFADE_ANIMATION_TIME_DURATION))
                    .into(ivWiki)
            }
        }

        fun setClickListener(page: Page) {
            binding.apply {
                root.setOnClickListener {
                    listener.invoke(page)
                }
            }
        }

    }
}