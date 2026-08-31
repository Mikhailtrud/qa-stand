package com.qastand.android

import android.content.Context

object AuthTokenStore {
    const val PREFERENCES_NAME = "auth_prefs"
    const val TOKEN_KEY = "auth_token"

    fun get(context: Context): String? =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getString(TOKEN_KEY, null)
            ?.takeIf { it.isNotBlank() }

    fun save(context: Context, token: String) {
        require(token.isNotBlank()) { "Token must not be blank" }
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    fun clear(context: Context) {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(TOKEN_KEY)
            .apply()
    }
}
