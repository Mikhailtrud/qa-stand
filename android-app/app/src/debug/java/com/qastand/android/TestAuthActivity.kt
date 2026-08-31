package com.qastand.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class TestAuthActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = intent.getStringExtra(EXTRA_TOKEN)
        if (token.isNullOrBlank()) {
            finish()
            return
        }

        AuthTokenStore.save(this, token)
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            },
        )
        finish()
    }

    companion object {
        const val EXTRA_TOKEN = "com.qastand.android.extra.AUTH_TOKEN"
    }
}
