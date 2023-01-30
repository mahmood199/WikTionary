package com.example.androidapplicationtemplate.core.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class ApplicationPreferences @Inject constructor(private val context: Context) {

    companion object {
        private const val MODE = Context.MODE_PRIVATE
        private const val USER_PREFERENCES = "USER_PREF"

        //Values Key
        private const val KEY_CUSTOMER_NAME = "CUSTOMER_NAME"
    }

    private val userPreferences = context.getSharedPreferences(USER_PREFERENCES, MODE)

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(
        operation: (SharedPreferences.Editor) -> Unit,
    ) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userName: String?
        get() {
            return userPreferences.getString(KEY_CUSTOMER_NAME, "")
        }
        set(value) = userPreferences.edit {
            it.putString(KEY_CUSTOMER_NAME, value)
        }

}