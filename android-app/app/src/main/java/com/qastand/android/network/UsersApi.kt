package com.qastand.android.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
)

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: String,
)

data class UpdateUserRequest(
    val name: String,
    val email: String,
    val role: String,
)

interface UsersApi {
    @GET("users")
    suspend fun getUsers(@Header("Authorization") authorization: String): List<UserResponse>

    @POST("users")
    suspend fun createUser(
        @Header("Authorization") authorization: String,
        @Body request: CreateUserRequest,
    ): UserResponse

    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") id: Long,
        @Header("Authorization") authorization: String,
        @Body request: UpdateUserRequest,
    ): UserResponse

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Long,
        @Header("Authorization") authorization: String,
    )
}
