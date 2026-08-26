package com.qastand.android.network

import com.qastand.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    val usersApi: UsersApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }
}
