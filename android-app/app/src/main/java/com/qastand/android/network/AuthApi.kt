package com.qastand.android.network

import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(
    val email: String,
    val password: String,
)

data class LoginResponse(
    val token: String,
    val role: String,
)

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
