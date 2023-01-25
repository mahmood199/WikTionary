package com.example.androidapplicationtemplate.util

import android.R
import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

object SnackBarBuilder {
    fun getSnackbar(
        activity: Activity,
        message: String,
        duration: Int,
    ): Snackbar {
        val snackbar: Snackbar = Snackbar.make(activity.findViewById(R.id.content), message, duration)
        val group = snackbar.view as ViewGroup
        group.setBackgroundColor(ContextCompat.getColor(activity.applicationContext, R.color.holo_red_dark))

        val tv = group.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        tv.maxLines = 10
        tv.setTextColor(Color.WHITE)
        val params = group.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
        group.layoutParams = params
        return snackbar
    }
}