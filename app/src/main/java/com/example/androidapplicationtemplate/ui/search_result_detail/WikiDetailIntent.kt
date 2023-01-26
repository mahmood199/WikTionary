package com.example.androidapplicationtemplate.ui.search_result_detail

import android.content.Intent

sealed class WikiDetailIntent {
    data class GetArgs(val intent: Intent?) : WikiDetailIntent()
}